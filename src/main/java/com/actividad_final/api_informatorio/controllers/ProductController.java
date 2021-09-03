package com.actividad_final.api_informatorio.controllers;

import com.actividad_final.api_informatorio.dto.ProductDTO;
import com.actividad_final.api_informatorio.exceptions.ResourceNotFound;
import com.actividad_final.api_informatorio.models.Product;
import com.actividad_final.api_informatorio.repositories.ProductRepository;
import com.actividad_final.api_informatorio.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/v1")
public class ProductController {

   @Autowired
   private ProductRepository productRepository;

   @Autowired
   private ProductService productService;


   @GetMapping(value = "/products/{productId}")
   public ResponseEntity<?> getProduct(@PathVariable("productId") Long productId) {
      Optional<Product> product = productRepository.findById(productId);
      if (product.isEmpty()) {
         throw new ResourceNotFound("¡El producto solicitado no existe!");
      }
   return ResponseEntity.status(HttpStatus.OK).body(product);
   }

   @GetMapping(value = "/products")
   public ResponseEntity<?> getFilteredProduct(@RequestParam("name") Optional<String> requestName) {
      if (requestName.isPresent()) {
         return ResponseEntity.status(HttpStatus.OK).body(productRepository.findByNameContaining(requestName.get()));
      }
      return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
   }

   @GetMapping(value = "/products/unpublished")
   public ResponseEntity<?> getProductsUnpublished() {
      return ResponseEntity.status(HttpStatus.OK).body(productRepository.findByIsPublished(false));
   }

   @PostMapping(value = "/products")
   public ResponseEntity<?> createProduct(@Valid @RequestBody Product requestProduct) {
      requestProduct.setPublished(true);
      return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(requestProduct));
   }

   @PutMapping(value = "/products/{productId}")
   public ResponseEntity<?> updateProduct(@PathVariable("productId") Long productId,
                                          @Valid @RequestBody ProductDTO requestProdcutDTO) {
      Optional<Product> product = productRepository.findById(productId);
      if (product.isEmpty()) {
         throw new ResourceNotFound("¡El producto solicitado no existe!");
      }
      productService.updatePreparation(product.get(), requestProdcutDTO);
      return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product.get()));
   }

   @DeleteMapping(value = "/products/{productId}")
   public ResponseEntity<?> deleteProduct(@PathVariable("productId") long productId) {
      Optional<Product> product = productRepository.findById(productId);
      if (product.isEmpty()) {
         throw new ResourceNotFound("¡El producto solicitado no existe!");
      }
      productRepository.delete(product.get());
      return ResponseEntity.status(HttpStatus.OK).build();
   }
}
