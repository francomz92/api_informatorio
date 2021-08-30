package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.ProductDTO;
import com.actividad_final.api_informatorio.models.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

   public void updatePreparation(Product product, ProductDTO requestProductDTO) {
      product.setName(requestProductDTO.getName());
      product.setUnitPrice(requestProductDTO.getUnitPrice());
      product.setDescription(requestProductDTO.getDescription());
      product.setBody(requestProductDTO.getBody());
      if (requestProductDTO.isPublished()) {
         product.setPublished(true);
      }
   }
}
