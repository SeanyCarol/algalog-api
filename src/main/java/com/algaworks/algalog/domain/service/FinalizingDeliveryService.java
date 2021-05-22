package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.repository.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalizingDeliveryService {

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired  
  private SearchDeliveryService searchDeliveryService;

  @Transactional
  public void finish(Long deliveryId) {
    Delivery delivery = searchDeliveryService.search(deliveryId);

    delivery.finish();

    deliveryRepository.save(delivery);
  }
}
