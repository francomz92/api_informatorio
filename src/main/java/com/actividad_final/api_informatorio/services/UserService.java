package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.UserDTO;
import com.actividad_final.api_informatorio.models.User;
import com.actividad_final.api_informatorio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   /*
   *  filterUsers -> ResponseEntity whit data checked; filter by city or dates or both
   *  @param Optional<String> city
   *  @param Optional<String> requestStartDate
   *     - format: dd-MM-yyyy
   *  @param Optional<String> requestEndDate
   *     - format: dd-MM-yyyy
   * */
   public ResponseEntity<?> filterUsers(Optional<String> city, Optional<String> requestStartDate, Optional<String> requestEndDate) {
      if (requestStartDate.isEmpty() || requestEndDate.isEmpty()) {
         return ResponseEntity.status(HttpStatus.OK).body(userRepository.findByCity(city.get()));
      }
      LocalDateTime startDate = startDateProssesing(requestStartDate.get());
      LocalDateTime endDate = endDateProssesing(requestEndDate.get());
      if (city.isEmpty()) {
         return ResponseEntity.status(HttpStatus.OK).body(userRepository.findByRegistrationDateBetween(startDate, endDate));
      }
      return ResponseEntity.status(HttpStatus.OK).body(userRepository.findByCityAndRegistrationDateBetween(city.get(), startDate, endDate));
   }

   /*
    *  startDateProssesing -> LocalDateTime with LocalTime at start of the day
    *  @param String requestDate
 *       - format: dd-MM-yyyy
    */
   public LocalDateTime startDateProssesing(String requestDate) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      return LocalDateTime.of(LocalDate.parse(requestDate, formatter), LocalTime.MIN);
   }

   /*
   *  endDateProssesing -> LocalDateTime with LocalTime at end of the day
   *  @param String requestData
    *    - format: dd-MM-yyyy
   */
   public LocalDateTime endDateProssesing(String requestDate) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      return LocalDateTime.of(LocalDate.parse(requestDate, formatter), LocalTime.MAX);
   }

   /*
   *  updatePreparation -> prepare data for user update
   *  @param User user
   *  @param UserDTO requestUserDTO
   *     - String firstName
   *     - String lastName
   *     - String city
   *     - String province
   *     - String country
   */
   public void updatePreparation(User user, UserDTO requestUserDTO) {
      user.setFirstName(requestUserDTO.getFirstName());
      user.setLastName(requestUserDTO.getLastName());
      user.setCity(requestUserDTO.getCity());
      user.setProvince(requestUserDTO.getProvince());
      user.setCountry(requestUserDTO.getCountry());
   }
}
