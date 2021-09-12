package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.ProductDTO;
import com.actividad_final.api_informatorio.models.Cart;
import com.actividad_final.api_informatorio.models.Product;
import com.actividad_final.api_informatorio.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

   @Autowired
   private CartRepository cartRepository;

   /*
   *  updateProduct -> prepare date for product update
   *  @param Product product
   *  @param ProductDTO requestProductDTO
   *     - String name
   *     - String description
   *     - String body
   *     - BigDecimal utiPrice
   *     - boolean isPublished
   * */
   public void updatePreparation(Product product, ProductDTO requestProductDTO) {
      product.setName(requestProductDTO.getName());
      product.setUnitPrice(requestProductDTO.getUnitPrice());
      product.setDescription(requestProductDTO.getDescription());
      product.setBody(requestProductDTO.getBody());
      if (requestProductDTO.isPublished()) {
         product.setPublished(true);
      }
      productRelationsChecker(product);
   }

   /*
    *  productRelationsChecker -> check and update only carts products
    *  @param Product product
    * */
   public void productRelationsChecker(Product product) {
      List<Cart> carts = cartRepository.findAll();
      for (Cart cart: carts) {
         cart.getDetails().forEach(detail -> {
            if (detail.getFK_product().getId() == product.getId()) {
               detail.getFK_product().setName(product.getName());
               detail.getFK_product().setUnitPrice(product.getUnitPrice());
               detail.getFK_product().setDescription(product.getDescription());
               detail.getFK_product().setBody(product.getBody());
               if (product.isPublished()) {
                  detail.getFK_product().setPublished(true);
               }
               detail.defaultUnitPrice();
            }
         });
      }
   }
}
