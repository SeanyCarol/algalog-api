package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@Embeddable
public class Recipient {

  @NotBlank
  @Column(name = "recipient_name")
  private String name;

  @NotBlank
  @Column(name = "recipient_public_area")
  private String publicArea;

  @NotBlank
  @Column(name = "recipient_number")
  private String number;

  @Column(name = "recipient_address_complement")
  private String addressComplement;

  @NotBlank
  @Column(name = "recipient_neighborhood")
  private String neighborhood;
}
