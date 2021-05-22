package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.RecipientModel;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import com.algaworks.algalog.domain.service.DeliveryRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entregas")
public class DeliveryController {
  
  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DeliveryRequestService deliveryRequestService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Delivery request(@Valid @RequestBody Delivery delivery) {
    return deliveryRequestService.request(delivery);
  }

  @GetMapping
  public List<Delivery> list() {
    return deliveryRepository.findAll();
  }

  @GetMapping("/{deliveryId}")
  public ResponseEntity<DeliveryModel> get(@PathVariable Long deliveryId) {
    return deliveryRepository.findById(deliveryId)
      .map(delivery -> {
        DeliveryModel deliveryModel = new DeliveryModel();
        deliveryModel.setId(delivery.getId());
        deliveryModel.setClientName(delivery.getClient().getName());
        deliveryModel.setRecipient(new RecipientModel());
        deliveryModel.getRecipient().setName(delivery.getRecipient().getName());
        deliveryModel.getRecipient().setPublicArea(delivery.getRecipient().getPublicArea());
        deliveryModel.getRecipient().setNumber(delivery.getRecipient().getNumber());
        deliveryModel.getRecipient().setAddressComplement(delivery.getRecipient().getAddressComplement());
        deliveryModel.getRecipient().setNeighborhood(delivery.getRecipient().getNeighborhood());
        deliveryModel.setRate(delivery.getRate());
        deliveryModel.setStatus(delivery.getStatus());
        deliveryModel.setOrderDate(delivery.getOrderDate());
        deliveryModel.setFinalizationDate(delivery.getFinalizationDate());

        return ResponseEntity.ok(deliveryModel);
      })
      .orElse(ResponseEntity.notFound().build());
  }
}
