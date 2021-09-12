package com.actividad_final.api_informatorio.controllers;

import com.actividad_final.api_informatorio.dto.OrdenDTO;
import com.actividad_final.api_informatorio.exceptions.ResourceNotFound;
import com.actividad_final.api_informatorio.models.Orden;
import com.actividad_final.api_informatorio.models.User;
import com.actividad_final.api_informatorio.repositories.OrdenRepository;
import com.actividad_final.api_informatorio.repositories.UserRepository;
import com.actividad_final.api_informatorio.services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class OrdenController {

   @Autowired
   private OrdenRepository ordenRepository;
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private OrdenService ordenService;

   @GetMapping(value = "/users/{userId}/ordens")
   public ResponseEntity<?> getOrders(@PathVariable("userId") Long userId) {
      return ResponseEntity.ok(ordenRepository.findAll());
   }

   @GetMapping(value = "/users/{userId}/ordens/{orderId}")
   public ResponseEntity<?> getOrder(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
      Optional<User> user = userRepository.findById(userId);
      Optional<Orden> order = ordenRepository.findById(orderId);
      if (user.isEmpty() || order.isEmpty() || !user.get().getOrdens().contains(order.get())) {
         throw new ResourceNotFound("¡Los datos de la compra no coinciden!");
      }
      return ResponseEntity.ok(order);
   }

   @PostMapping(value = "/users/{userId}/ordens")
   public ResponseEntity<?> createOrder(@PathVariable("userId") Long userId, @Valid @RequestBody OrdenDTO ordenDTO) {
      Optional<User> user = userRepository.findById(userId);
      if (user.isEmpty()) {
         throw new ResourceNotFound("¡No se encontro el usuario solicitado!");
      }
      Orden orden = ordenService.createOrder(ordenDTO, user.get());
      return ResponseEntity.status(HttpStatus.CREATED).body(ordenRepository.save(orden));
   }

   @PutMapping(value = "/users/{userId}/ordens/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> updateOrder(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId, @Valid @RequestBody OrdenDTO ordenDTO) {
      Optional<User> user = userRepository.findById(userId);
      Optional<Orden> order = ordenRepository.findById(orderId);
      if (user.isEmpty() || order.isEmpty() || !user.get().getOrdens().contains(order.get())) {
         throw new ResourceNotFound("¡No existe el recurso solicitado!");
      }
      return ResponseEntity.ok(order);
   }

   @DeleteMapping(value = "/users/{userId}/ordens/{orderId}")
   public ResponseEntity<?> deleteOrder(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
      Optional<User> user = userRepository.findById(userId);
      Optional<Orden> order = ordenRepository.findById(orderId);
      if (user.isEmpty() || order.isEmpty() || !user.get().getOrdens().contains(order.get())) {
         throw new ResourceNotFound("¡No existe el recurso solicitado!");
      }
      user.get().getOrdens().remove(order.get());
      ordenRepository.delete(order.get());
      return ResponseEntity.ok().build();
   }

}
