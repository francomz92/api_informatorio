package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.CartDTO;
import com.actividad_final.api_informatorio.models.Cart;
import com.actividad_final.api_informatorio.models.PurchaseDetail;
import com.actividad_final.api_informatorio.models.User;
import com.actividad_final.api_informatorio.repositories.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

   @Autowired
   private PurchaseDetailRepository purchaseDetailRepository;


   /*
    *  savePreparation -> prepare data for cart save
    *     - Set detail unit price
    *  @param Cart requestCart
    * */
   public void savePreparation(User user, Cart requestCart) {
      requestCart.setUser(user);
      for (PurchaseDetail detail: requestCart.getDetails()) {
         detail.defaultUnitPrice();
         detail.setCart(requestCart);
//         purchaseDetailRepository.save(detail);
      }
      user.addCart(requestCart);
   }

   /*
    *  updatePreparation -> prepare data for cart update
    *  @param Cart cart
    *  @param CartDTO requestCart
    *     - List<PurchaseDetail> details
    * */
   public void updatePreparation(Cart cart, CartDTO requestCart) {
      for (PurchaseDetail detail: requestCart.getDetails()) {
         detail.defaultUnitPrice();
         detail.setCart(cart);
         if (detail.getAmount().equals(0)) { requestCart.getDetails().remove(detail); }
      }
      cart.setDetails(requestCart.getDetails());
   }
}
