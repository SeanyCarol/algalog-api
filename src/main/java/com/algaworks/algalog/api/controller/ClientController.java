package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algalog.domain.model.Client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  @PersistenceContext
  private EntityManager entityManager;

  @GetMapping("/clientes")
  public List<Client> listar() {
    return entityManager.createQuery("from Client", Client.class).getResultList();
  }
}
