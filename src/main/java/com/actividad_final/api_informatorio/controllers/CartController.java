package com.actividad_final.api_informatorio.controllers;

import com.actividad_final.api_informatorio.dto.CartDTO;
import com.actividad_final.api_informatorio.exceptions.ResourceNotFound;
import com.actividad_final.api_informatorio.models.Cart;
import com.actividad_final.api_informatorio.models.User;
import com.actividad_final.api_informatorio.repositories.CartRepository;
import com.actividad_final.api_informatorio.repositories.UserRepository;
import com.actividad_final.api_informatorio.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class CartController {

   @Autowired
   private CartRepository cartRepository;
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private CartService cartService;


   @GetMapping(value = "/users/{userId}/carts/{cartId}")
   public ResponseEntity<?> getCart(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId) {
      Optional<User> user = userRepository.findById(userId);
      Optional<Cart> cart = cartRepository.findById(cartId);
      if (user.isEmpty() || !user.get().getCarts().contains(cart.get())) {
         throw new ResourceNotFound("¡El carrito no existe!");
      }
      return ResponseEntity.ok(cart);
   }

   @GetMapping(value = "/users/{userId}/carts")
   public ResponseEntity<?> getAllCarts(@PathVariable("userId") Long userId) {
      Optional<User> user = userRepository.findById(userId);
      if (user.isEmpty()) {
         throw new ResourceNotFound("¡El carrito no existe!");
      }
      List<Cart> carts = user.get().getCarts();
      return ResponseEntity.ok(carts);
   }

   @PostMapping(value = "/users/{userId}/carts")
   public ResponseEntity<?> createCart(@PathVariable("userId") Long userId,
                                       @Valid @RequestBody Cart requestCart) {
      Optional<User> user = userRepository.findById(userId);
      if (user.isEmpty()) {
         throw new ResourceNotFound("¡El usuario no existe!");
      }
      cartService.savePreparation(user.get(), requestCart);
      return ResponseEntity.status(HttpStatus.CREATED).body(cartRepository.save(requestCart));
   }

   @PutMapping(value = "/users/{userId}/carts/{cartId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<?> updateCart(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId,
                                       @Valid @RequestBody CartDTO requestCart) {
      Optional<User> user = userRepository.findById(userId);
      Optional<Cart> cart = cartRepository.findById(cartId);
      if (user.isEmpty() || cart.isEmpty() || !user.get().getCarts().contains(cart.get())) {
         throw new ResourceNotFound("¡No se encontró el carrito solicitado!");
      }
      cartService.updatePreparation(cart.get(), requestCart);
      return ResponseEntity.ok(cartRepository.save(cart.get()));
   }

   @DeleteMapping(value = "/users/{userId}/carts/{cartId}")
   public ResponseEntity<?> deleteCart(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId) {
      Optional<User> user = userRepository.findById(userId);
      Optional<Cart> cart = cartRepository.findById(cartId);
      if (user.isEmpty() || cart.isEmpty() || !user.get().getCarts().contains(cart.get())) {
         throw new ResourceNotFound("¡El carrito que desea eliminar no existe!");
      }
      user.get().getCarts().remove(cart.get());
      cartRepository.delete(cart.get());
      return ResponseEntity.ok().build();
   }
}
