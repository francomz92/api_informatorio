package com.actividad_final.api_informatorio.dto;

import com.actividad_final.api_informatorio.models.PurchaseDetail;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {

   private List<PurchaseDetail> details = new ArrayList<>();

   public CartDTO() {}


   public List<PurchaseDetail> getDetails() { return details; }

   public void setDetails(List<PurchaseDetail> details) { this.details = details; }
}
