package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.DeliveryStatus;
import com.algaworks.algalog.domain.repository.DeliveryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryRequestService {
  
  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private ClientCatalogService clientCatalogService;

  @Transactional
  public Delivery request(Delivery delivery) {
    Client client = clientCatalogService.get(delivery.getClient().getId());
    
    delivery.setClient(client);
    delivery.setStatus(DeliveryStatus.PENDING);
    delivery.setOrderDate(OffsetDateTime.now());

    return deliveryRepository.save(delivery);
  }
}
