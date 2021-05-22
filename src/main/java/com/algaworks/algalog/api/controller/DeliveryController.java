package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.api.assembler.DeliveryAssembler;
import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInput;
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

  @Autowired
  private DeliveryAssembler deliveryAssembler;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DeliveryModel request(@Valid @RequestBody DeliveryInput deliveryInput) {
    Delivery newDelivery = deliveryAssembler.toEntity(deliveryInput);
    Delivery requestedDelivery = deliveryRequestService.request(newDelivery);
    
    return deliveryAssembler.toModel(requestedDelivery);
  }

  @GetMapping
  public List<DeliveryModel> list() {
    return deliveryAssembler.toCollectionModel(deliveryRepository.findAll());
  }

  @GetMapping("/{deliveryId}")
  public ResponseEntity<DeliveryModel> search(@PathVariable Long deliveryId) {
    return deliveryRepository.findById(deliveryId)
      .map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery)))
      .orElse(ResponseEntity.notFound().build());
  }
}
