package com.actividad_final.api_informatorio.repositories;

import com.actividad_final.api_informatorio.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   Iterable<Product> findByNameContaining(String name);

   Iterable<Product> findByIsPublished(boolean isPublished);
}
