package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.OrdenDTO;
import com.actividad_final.api_informatorio.models.Cart;
import com.actividad_final.api_informatorio.models.Orden;
import com.actividad_final.api_informatorio.models.User;
import com.actividad_final.api_informatorio.repositories.CartRepository;
import com.actividad_final.api_informatorio.repositories.OrdenRepository;
import com.actividad_final.api_informatorio.repositories.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {

   @Autowired
   private PurchaseDetailRepository purchaseDetailRepository;
   @Autowired
   private OrdenRepository ordenRepository;
   @Autowired
   private CartRepository cartRepository;


   public Orden createOrder(OrdenDTO ordenDTO, User user) {
      Orden orden = new Orden();
      orden.setId(ordenDTO.getCart().getId());
      orden.setObservation(ordenDTO.getObservation());
      orden.setDetails(ordenDTO.getCart().getDetails());
      orden.setUser(user);

      updateUserCartsAndUserOrders(user, orden, ordenDTO.getCart());

      orden.getDetails().forEach(detail -> {
         detail.setOrder(orden);
      });
      return orden;
   }

   public void updateUserCartsAndUserOrders(User user, Orden orden, Cart requestCart) {
      user.getCarts().remove(requestCart);
      Cart cart = cartRepository.getById(orden.getId());
//      cartRepository.delete(cart);
      cart.setUser(null);
   }
}
