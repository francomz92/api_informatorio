package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.ProductDTO;
import com.actividad_final.api_informatorio.models.Product;
import com.actividad_final.api_informatorio.models.PurchaseDetail;
import com.actividad_final.api_informatorio.repositories.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

   @Autowired
   private PurchaseDetailRepository purchaseDetailRepository;

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
      updatePurchaseDetail();
   }

   public void updatePurchaseDetail() {
      Iterable<PurchaseDetail> details = purchaseDetailRepository.findAll();
      details.forEach(PurchaseDetail::defaultUnitPrice);
   }
}
