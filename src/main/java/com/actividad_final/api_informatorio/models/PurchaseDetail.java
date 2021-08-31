package com.actividad_final.api_informatorio.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class PurchaseDetail {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Product.class)
   @JoinColumn(name = "FK_product")
   private Product product;
   @Positive
   private Integer amount;
   @Transient
   private BigDecimal subtotal;
   @Positive
   private BigDecimal unitPrice;


   public PurchaseDetail () {}


   // Getters

   public Long getId() { return id; }

   public Product getFK_product() { return product; }

   public Integer getAmount() { return amount; }

   public BigDecimal getSubtotal() { return unitPrice.multiply(BigDecimal.valueOf(amount)); }

   public BigDecimal getUnitPrice() { return unitPrice; }

   // Setters

   public void setId(Long id) {
      this.id = id;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public void setAmount(Integer amount) {
      this.amount = amount;
   }

   public void setUnitPrice(BigDecimal unitPrice) {
      this.unitPrice = unitPrice;
   }

   // Third Method

   public void defaultUnitPrice() {
      this.unitPrice = this.product.getUnitPrice();
   }
}
