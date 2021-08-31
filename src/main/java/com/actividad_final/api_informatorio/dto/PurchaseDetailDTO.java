package com.actividad_final.api_informatorio.dto;

import com.actividad_final.api_informatorio.models.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PurchaseDetailDTO {

   @NotNull
   @NotEmpty
   private Product product;
   @Positive
   private Integer amount;
   @Positive
   private BigDecimal unitPrice;


   public PurchaseDetailDTO() {}


   // Getters

   public Product getProduct() { return product; }

   public Integer getAmount() { return amount; }

   public BigDecimal getUnitPrice() { return unitPrice; }

   // Setters

   public void setProduct(Product product) {
      this.product = product;
   }

   public void setAmount(Integer amount) {
      this.amount = amount;
   }

   public void setUnitPrice(BigDecimal unitPrice) {
      this.unitPrice = unitPrice;
   }
}
