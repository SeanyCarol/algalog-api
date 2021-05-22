package com.algaworks.algalog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algalog.domain.model.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryModel {
  
  private Long id;
  private ClienteSummaryModel client;
  private RecipientModel recipient;
  private BigDecimal rate;
  private DeliveryStatus status;
  private OffsetDateTime orderDate;
  private OffsetDateTime finalizationDate;
}
