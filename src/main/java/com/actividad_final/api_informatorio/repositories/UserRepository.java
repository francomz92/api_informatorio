package com.actividad_final.api_informatorio.repositories;

import com.actividad_final.api_informatorio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Iterable<User> findByCity(String city);

   Iterable<User> findByRegistrationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

   Iterable<User> findByCityAndRegistrationDateBetween(String city, LocalDateTime startDate, LocalDateTime endDate);
}
