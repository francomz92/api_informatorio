package com.actividad_final.api_informatorio.repositories;

import com.actividad_final.api_informatorio.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
