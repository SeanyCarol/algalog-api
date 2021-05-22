package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

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
  public ResponseEntity<Delivery> get(@PathVariable Long deliveryId) {
    return deliveryRepository.findById(deliveryId)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }
}
