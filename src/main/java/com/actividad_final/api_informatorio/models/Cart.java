package com.actividad_final.api_informatorio.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
   @JoinColumn(name = "FK_user")
   private User user;
   @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = PurchaseDetail.class)
   private List<PurchaseDetail> details = new ArrayList<>();

   @CreationTimestamp
   private LocalDateTime creationDate;
   @Transient
   private String customer;
   @Transient
   private BigDecimal total;


   public Cart() {}


   // Getters

   public Long getId() { return id; }

   public List<PurchaseDetail> getDetails() { return details; }

   public LocalDateTime getCreationDate() { return creationDate; }

   public String getCustomer() { return user.getFirstName().concat(" ").concat(user.getLastName()); }

   public BigDecimal getTotal() {
      BigDecimal finalTotal = BigDecimal.ZERO;
      for (PurchaseDetail detail: this.details) {
         finalTotal = finalTotal.add(detail.getSubtotal());
      }
      return finalTotal;
   }

   // Setters

   public void setId(Long id) {
      this.id = id;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public void setDetails(List<PurchaseDetail> details) {
      this.details = details;
   }

   public void setCreationDate(LocalDateTime creationDate) {
      this.creationDate = creationDate;
   }
}
