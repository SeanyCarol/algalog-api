package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.api.assembler.OccurrenceAssembler;
import com.algaworks.algalog.api.model.OccurrenceModel;
import com.algaworks.algalog.api.model.input.OccurrenceInput;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.Occurrence;
import com.algaworks.algalog.domain.service.OccurrenceRegistrationService;
import com.algaworks.algalog.domain.service.SearchDeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entregas/{deliveryId}/ocorrencias")
public class OccurrenceController {
  
  @Autowired
  private SearchDeliveryService searchDeliveryService;

  @Autowired
  private OccurrenceRegistrationService occurrenceRegistrationService;

  @Autowired
  private OccurrenceAssembler occurrenceAssembler;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OccurrenceModel register(@PathVariable Long deliveryId, @Valid @RequestBody OccurrenceInput occurrenceInput) {
    
    Occurrence recordedOccurrence = occurrenceRegistrationService.
      register(deliveryId, occurrenceInput.getDescription());
    
    return occurrenceAssembler.toModel(recordedOccurrence);
  }

  @GetMapping
  public List<OccurrenceModel> list(@PathVariable Long deliveryId) {
    Delivery delivery = searchDeliveryService.search(deliveryId);

    return occurrenceAssembler.toCollectionModel(delivery.getOccurrences());
  }
}
