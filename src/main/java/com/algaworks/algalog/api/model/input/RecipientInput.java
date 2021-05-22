package com.algaworks.algalog.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientInput {

  @NotBlank
  private String name;

  @NotBlank
  private String publicArea;

  @NotBlank
  private String number;

  private String addressComplement;

  @NotBlank
  private String neighborhood;
}
