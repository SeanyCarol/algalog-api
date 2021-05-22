package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algalog.api.model.OccurrenceModel;
import com.algaworks.algalog.domain.model.Occurrence;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OccurrenceAssembler {
  
  @Autowired
  private ModelMapper modelmapper;

  public OccurrenceModel toModel(Occurrence occurrence) {
    return modelmapper.map(occurrence, OccurrenceModel.class);
  }

  public List<OccurrenceModel> toCollectionModel(List<Occurrence>  occurrences) {
    return occurrences.stream()
      .map(this::toModel)
      .collect(Collectors.toList());
  }
}
