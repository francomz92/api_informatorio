package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.PurchaseDetailDTO;
import com.actividad_final.api_informatorio.models.PurchaseDetail;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailService {


   /*
   *  savePreparation -> prepare data for detail save
   *     - Set unit price
   *     - Set subtotal
   *  @param PurchaseDetail requestDetail
   * */
   public void savePreparation(PurchaseDetail requestDetail) {
      requestDetail.defaultUnitPrice();
   }

   /*
   *  updatePreparation -> prepare data for detail update
   *  @param PurchaseDetail detail
   *  @param PurchaseDetailDTO requestDetail
   *     - Product product
   *     - Integer amount
   *     - BigDecimal unitPrice
   * */
   public void updatePreparation(PurchaseDetail detail, PurchaseDetailDTO requestDetail) {
      detail.setProduct(requestDetail.getProduct());
      detail.defaultUnitPrice();
      detail.setAmount(requestDetail.getAmount());
   }
}
