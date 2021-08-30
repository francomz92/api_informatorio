package com.actividad_final.api_informatorio.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Length(max = 25, message = "¡Máximo 25 caracteres!")
   private String name;
   @Length(max = 150, message = "¡Máximo 150 caracteres!")
   private String description;
   @Positive
   private BigDecimal unitPrice;
   @Length(max = 500, message = "¡Máximo 500 caracteres!")
   private String body;
   @CreationTimestamp
   private LocalDateTime registrationDate;
   private boolean isPublished;

   public Product() {}

   // Getters

   public Long getId() { return id; }

   public String getName() { return name; }

   public String getDescription() { return description; }

   public BigDecimal getUnitPrice() { return unitPrice; }

   public String getBody() { return body; }

   public LocalDateTime getRegistrationDate() { return registrationDate; }

   public boolean isPublished() { return isPublished; }

   // Setters


   public void setId(Long id) {
      this.id = id;
   }

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

   public void setRegistrationDate(LocalDateTime registrationDate) {
      this.registrationDate = registrationDate;
   }

   public void setPublished(boolean published) {
      isPublished = published;
   }
}
