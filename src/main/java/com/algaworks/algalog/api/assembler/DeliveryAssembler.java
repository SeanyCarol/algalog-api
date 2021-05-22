package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.api.model.input.DeliveryInput;
import com.algaworks.algalog.domain.model.Delivery;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryAssembler {
  
  @Autowired
  private ModelMapper modelMapper;

  public DeliveryModel toModel(Delivery delivery) {
    return modelMapper.map(delivery, DeliveryModel.class);
  }

  public List<DeliveryModel> toCollectionModel(List<Delivery>  deliveries) {
    return deliveries.stream()
      .map(this::toModel)
      .collect(Collectors.toList());
  }

  public Delivery toEntity(DeliveryInput deliveryiInput) {
    return modelMapper.map(deliveryiInput, Delivery.class);
  }
}
