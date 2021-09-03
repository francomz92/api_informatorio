package com.actividad_final.api_informatorio.controllers;

import com.actividad_final.api_informatorio.dto.UserDTO;
import com.actividad_final.api_informatorio.exceptions.ResourceNotFound;
import com.actividad_final.api_informatorio.models.User;
import com.actividad_final.api_informatorio.repositories.UserRepository;
import com.actividad_final.api_informatorio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private UserService userService;


   @GetMapping(value = "/users/{userId}")
   public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
      Optional<User> user = userRepository.findById(userId);
      if (user.isEmpty()) {
         throw new ResourceNotFound("¡El usuario solicitado no existe!");
      }
      return ResponseEntity.status(HttpStatus.OK).body(user);
   }

   @GetMapping(value = "/users")
   public ResponseEntity<?> getFilteredUsers(@RequestParam("city") Optional<String> city,
                                             @RequestParam("startDate") Optional<String> requestStartDate,
                                             @RequestParam("endDate") Optional<String> requestEndDate) {
      if (city.isPresent() || requestStartDate.isPresent() && requestEndDate.isPresent()) {
         return userService.filterUsers(city, requestStartDate, requestEndDate);
      }
      return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
   }

   @PostMapping(value = "/users")
   public ResponseEntity<?> createNewUser(@Valid @RequestBody User requestUser) {
      return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(requestUser));
   }

   @PutMapping(value = "/users/{userId}")
   public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody UserDTO requestUserDTO) {
      Optional<User> user = userRepository.findById(userId);
      if (user.isEmpty()) {
         throw new ResourceNotFound("¡El usuario solicitado no existe!");
      }
      userService.updatePreparation(user.get(), requestUserDTO);
      return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user.get()));
   }

   @DeleteMapping(value = "/users/{userId}")
   public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
      Optional<User> user = userRepository.findById(userId);
      if (user.isEmpty()) {
         throw new ResourceNotFound("¡El usuario solicitado no existe!");
      }
      userRepository.delete(user.get());
      return ResponseEntity.status(HttpStatus.OK).build();
   }
}
