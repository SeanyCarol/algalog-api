package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.EntityNotFoundException;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.repository.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchDeliveryService {

  @Autowired
  private DeliveryRepository deliveryRepository;
  
  public Delivery search(Long deliveryId) {
    return deliveryRepository.findById(deliveryId)
      .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
  }
}
