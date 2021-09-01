package com.actividad_final.api_informatorio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @JsonIgnore
   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Cart.class)
   private List<Cart> carts = new ArrayList<>();

   @Length(max = 25, message = "Máximo 25 caracteres")
   private String firstName;
   @Length(max = 25, message = "Máximo 25 caracteres")
   private String lastName;
   @Email
   @NotNull
   @NotEmpty
   @Length(max = 25, message = "Máximo 25 caracteres")
   @Column(unique = true)
   private String email;
   @NotNull
   @Length(min = 8, message = "Mínimo 8 caracteres")
   private String password;
   @CreationTimestamp
   private LocalDateTime registrationDate;
   @Length(max = 25, message = "Máximo 25 caracteres")
   private String city;
   @Length(max = 25, message = "Máximo 25 caracteres")
   private String province;
   @Length(max = 25, message = "Máximo 25 caracteres")
   private String country;


   public User() {}


   // Getters

   public Long getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getEmail() {
      return email;
   }

   public LocalDateTime getRegistrationDate() {
      return registrationDate;
   }

   public String getCity() {
      return city;
   }

   public String getProvince() {
      return province;
   }

   public String getCountry() {
      return country;
   }

   public List<Cart> getCarts() { return carts; }

   // Setters

   public void setId(Long id) {
      this.id = id;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setRegistrationDate(LocalDateTime registrationDate) {
      this.registrationDate = registrationDate;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public void setCountry(String country) { this.country = country; }

   public void setCarts(List<Cart> carts) { this.carts = carts; }

   // Third Methods

   public void addCart(Cart cart) { this.carts.add(cart); }
}