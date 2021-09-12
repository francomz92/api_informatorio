package com.actividad_final.api_informatorio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "detail")
public class PurchaseDetail {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Product.class)
   @JoinColumn(name = "FK_product", nullable = false)
   private Product product;
   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Cart.class)
   @JoinColumn(name = "FK_cart")
   private Cart cart;
   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY, targetEntity = Orden.class)
   @JoinColumn(name = "FK_order")
   private Orden orden;

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

   public void setCart(Cart cart) { this.cart = cart; }

   public void setOrder(Orden orden) { this.orden = orden; }

   // Third Method

   public void defaultUnitPrice() {
      this.unitPrice = this.product.getUnitPrice();
   }
}
