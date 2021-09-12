package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.CartDTO;
import com.actividad_final.api_informatorio.exceptions.BadRequest;
import com.actividad_final.api_informatorio.models.Cart;
import com.actividad_final.api_informatorio.models.Product;
import com.actividad_final.api_informatorio.models.PurchaseDetail;
import com.actividad_final.api_informatorio.models.User;
import com.actividad_final.api_informatorio.repositories.ProductRepository;
import com.actividad_final.api_informatorio.repositories.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

   @Autowired
   private PurchaseDetailRepository purchaseDetailRepository;
   @Autowired
   private ProductRepository productRepository;


   /*
    *  savePreparation -> prepare data for cart and purchase detail save
    *     - Set detail unit price
    *  @param Cart requestCart
    * */
   public void savePreparation(User user, Cart requestCart) {
      for (PurchaseDetail detail: requestCart.getDetails()) {
         Product product = productRepository.findById(detail.getFK_product().getId()).orElse(null);
         if (product == null) {
            throw new BadRequest("¡Información dañada!");
         }
         detail.setProduct(product);
         detail.defaultUnitPrice();
         detail.setCart(requestCart);
      }
      requestCart.setUser(user);
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
