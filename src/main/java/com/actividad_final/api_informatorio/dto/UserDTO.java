package com.actividad_final.api_informatorio.dto;

import org.hibernate.validator.constraints.Length;

public class UserDTO {

   @Length(max = 25, message = "Máximo 25 caracteres")
   private String firstName;
   @Length(max = 25, message = "Máximo 25 caracteres")
   private String lastName;
   @Length(max = 25, message = "Máximo 25 caracteres")
   private String city;
   @Length(max = 25, message = "Máximo 25 caracteres")
   private String province;
   @Length(max = 25, message = "Máximo 25 caracteres")
   private String country;


   public UserDTO() {}


   // Getters

   public String getFirstName() { return firstName; }

   public String getLastName() { return lastName; }

   public String getCity() { return city; }

   public String getProvince() { return province; }

   public String getCountry() { return country; }

   // Setters

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public void setCountry(String country) {
      this.country = country;
   }
}
