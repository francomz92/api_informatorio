package com.actividad_final.api_informatorio.services;

import com.actividad_final.api_informatorio.dto.OrdenDTO;
import com.actividad_final.api_informatorio.exceptions.BadRequest;
import com.actividad_final.api_informatorio.exceptions.ResourceNotFound;
import com.actividad_final.api_informatorio.models.Cart;
import com.actividad_final.api_informatorio.models.Orden;
import com.actividad_final.api_informatorio.models.PurchaseDetail;
import com.actividad_final.api_informatorio.models.User;
import com.actividad_final.api_informatorio.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {

   @Autowired
   private CartRepository cartRepository;


   public Orden createOrder(OrdenDTO ordenDTO, User user) {
      if (requestDataIsValid(ordenDTO)) {
         Cart cart = cartRepository.getById(ordenDTO.getCart().getId());
         if (cart.getId() != null && user.getCarts().contains(cart)) {
            Orden orden = new Orden();
            orden.setId(ordenDTO.getCart().getId());
            orden.setObservation(ordenDTO.getObservation());
            orden.setDetails(ordenDTO.getCart().getDetails());
            orden.setUser(user);

            updateUserCartsAndUserOrders(user, orden);

            orden.getDetails().forEach(detail -> {
               detail.setOrder(orden);
            });
            cartRepository.deleteById(orden.getId());

            return orden;
         }
         throw new ResourceNotFound("¡Este carrito ya se cerró!");
      }
      throw new BadRequest("¡La información enviada se encuentra dañada, vuelve a intentarlo más tarde!");
   }

   public void updateUserCartsAndUserOrders(User user, Orden orden) {
      Cart cart = cartRepository.getById(orden.getId());
      user.getCarts().remove(cart);
      cart.setUser(null);
   }

   public boolean requestDataIsValid(OrdenDTO ordenDTO) {
      boolean valid = true;
      if (ordenDTO.getCart().getId() == null|| ordenDTO.getCart().getDetails() == null) {
         valid = false;
      } else {
         for (PurchaseDetail detail: ordenDTO.getCart().getDetails()) {
            if (detail.getId() == null || detail.getFK_product() == null || detail.getUnitPrice() == null || detail.getAmount() == null) {
               valid = false;
               break;
            }
         }
      }
      return valid;
   }
}
