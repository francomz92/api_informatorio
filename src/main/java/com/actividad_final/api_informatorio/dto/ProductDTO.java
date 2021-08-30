package com.actividad_final.api_informatorio.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProductDTO {

   @Length(max = 25, message = "¡Máximo 25 caracteres!")
   private String name;
   @Length(max = 150, message = "¡Máximo 150 caracteres!")
   private String description;
   @Positive
   private BigDecimal unitPrice;
   @Length(max = 500, message = "¡Máximo 500 caracteres!")
   private String body;
   private boolean isPublished;

   public ProductDTO () {}


   // Getters

   public String getName() { return name; }

   public String getDescription() { return description; }

   public BigDecimal getUnitPrice() { return unitPrice; }

   public String getBody() { return body; }

   public boolean isPublished() { return isPublished; }

   // Setters

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setUnitPrice(BigDecimal unitPrice) {
      this.unitPrice = unitPrice;
   }

   public void setBody(String body) {
      this.body = body;
   }

   public void setPublished(boolean published) {
      isPublished = published;
   }
}
