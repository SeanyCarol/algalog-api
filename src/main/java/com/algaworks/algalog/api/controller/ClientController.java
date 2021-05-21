package com.algaworks.algalog.api.controller;

import java.util.List; 

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  @Autowired
  private ClientRepository clientRepository;

  @GetMapping("/clientes")
  public List<Client> listar() {
    return clientRepository.findAll();
  }
}
