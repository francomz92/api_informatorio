package com.actividad_final.api_informatorio.dto;

import com.actividad_final.api_informatorio.models.Cart;
import org.hibernate.validator.constraints.Length;

public class OrdenDTO {

   private Cart cart;
   @Length(max = 200)
   private String observation;


   public OrdenDTO() {}


   // Getters

   public Cart getCart() { return cart; }

   public String getObservation() { return observation; }

   // Setters


   public void setCart(Cart cart) {
      this.cart = cart;
   }

   public void setObservation(String observation) {
      this.observation = observation;
   }
}
