package com.actividad_final.api_informatorio.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orden")
public class Orden {

   @Id
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
   @JoinColumn(name = "FK_user")
   private User user;
   @OneToMany(mappedBy = "orden", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = PurchaseDetail.class)
   private List<PurchaseDetail> details = new ArrayList<>();

   @CreationTimestamp
   private LocalDateTime orderDate;
   private String observation;

   @Transient
   private String customer;
   @Transient
   private BigDecimal total;


   public Orden() {}


   // Getters

   public Long getId() { return id; }

   public List<PurchaseDetail> getDetails() { return details; }

   public LocalDateTime getOrderDate() { return orderDate; }

   public String getObservation() { return observation; }

   public String getCustomer() { return this.user.getFirstName().concat(" ").concat(this.user.getLastName()); }

   public BigDecimal getTotal() {
      BigDecimal finalTotal = BigDecimal.ZERO;
      for (PurchaseDetail detail: this.details) {
         finalTotal = finalTotal.add(detail.getSubtotal());
      }
      return finalTotal;
   }

   // Setters

   public void setId(Long id) { this.id = id; }

   public void setUser(User user) { this.user = user; }

   public void setDetails(List<PurchaseDetail> details) { this.details = details; }

   public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

   public void setObservation(String observation) { this.observation = observation; }
}
