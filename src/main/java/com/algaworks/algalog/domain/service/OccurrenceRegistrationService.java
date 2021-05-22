package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.Occurrence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OccurrenceRegistrationService {
  
  @Autowired
  private SearchDeliveryService searchDeliveryService;

  @Transactional
  public Occurrence register(Long deliveryId, String description) {
    Delivery delivery = searchDeliveryService.search(deliveryId);

    return delivery.addOccurrence(description);
  }
}
