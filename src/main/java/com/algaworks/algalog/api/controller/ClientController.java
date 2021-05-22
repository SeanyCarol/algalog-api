package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import com.algaworks.algalog.domain.service.ClientCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientController {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientCatalogService catalogoClientService;

  @GetMapping
  public List<Client> list() {
    return clientRepository.findAll();
  }
 
  @GetMapping("/{clientId}")
  public ResponseEntity<Client> get(@PathVariable Long clientId) {
    return clientRepository.findById(clientId)
      .map(client -> ResponseEntity.ok(client))
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Client add(@Valid @RequestBody Client client) {
    return catalogoClientService.save(client);
  }

  @PutMapping("/{clientId}")
  public ResponseEntity<Client> upgrade(@PathVariable Long clientId, @Valid @RequestBody Client client) {
    if(!clientRepository.existsById(clientId)) {
      return ResponseEntity.notFound().build();
    }

    client.setId(clientId);
    client = catalogoClientService.save(client);

    return ResponseEntity.ok(client);
  }

  @DeleteMapping("/{clientId}")
  public ResponseEntity<Void> remove(@PathVariable Long clientId) {
    if(!clientRepository.existsById(clientId)) {
      return ResponseEntity.notFound().build();
    }

    catalogoClientService.delete(clientId);
    
    return ResponseEntity.noContent().build();
  }
}
