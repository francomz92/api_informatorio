package com.actividad_final.api_informatorio.controllers;

import com.actividad_final.api_informatorio.dto.PurchaseDetailDTO;
import com.actividad_final.api_informatorio.exceptions.ResourceNotFound;
import com.actividad_final.api_informatorio.models.PurchaseDetail;
import com.actividad_final.api_informatorio.repositories.PurchaseDetailRepository;
import com.actividad_final.api_informatorio.services.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseDetailController {

   @Autowired
   private PurchaseDetailRepository purchaseDetailRepository;

   @Autowired
   private PurchaseDetailService purchaseDetailService;

   @GetMapping(value = "/details/{detailId}")
   public ResponseEntity<?> getDetail(@PathVariable("detailId") Long detailId) {
      Optional<PurchaseDetail> detail = purchaseDetailRepository.findById(detailId);
      if (detail.isEmpty()) {
         throw new ResourceNotFound("¡No existe el detalle solicitado!");
      }
      return ResponseEntity.status(HttpStatus.OK).body(detail);
   }

   @GetMapping(value = "/details")
   public ResponseEntity<?> getAllDetails() {
      return ResponseEntity.status(HttpStatus.OK).body(purchaseDetailRepository.findAll());
   }

   @PostMapping(value = "/details")
   public ResponseEntity<?> createDetail(@Valid @RequestBody PurchaseDetail requestDetail) {
      purchaseDetailService.savePreparation(requestDetail);
      return ResponseEntity.status(HttpStatus.CREATED).body(purchaseDetailRepository.save(requestDetail));
   }

   @PutMapping(value = "/details/{detailId}")
   public ResponseEntity<?> updateDetail(@PathVariable("detailId") Long detailId,
                                         @Valid @RequestBody PurchaseDetailDTO requestDetail) {
      Optional<PurchaseDetail> detail = purchaseDetailRepository.findById(detailId);
      if (detail.isEmpty()) {
         throw new ResourceNotFound("¡No existe el detalle solicitado!");
      }
      purchaseDetailService.updatePreparation(detail.get(), requestDetail);
      return ResponseEntity.status(HttpStatus.OK).body(purchaseDetailRepository.save(detail.get()));
   }

   @DeleteMapping(value = "/details/{detailId}")
   public ResponseEntity<?> deleteDetail(@PathVariable("detailId") Long detailId) {
      Optional<PurchaseDetail> detail = purchaseDetailRepository.findById(detailId);
      if (detail.isEmpty()) {
         throw new ResourceNotFound("¡No existe el detalle solicitado!");
      }
      purchaseDetailRepository.delete(detail.get());
      return ResponseEntity.status(HttpStatus.OK).build();
   }
}
