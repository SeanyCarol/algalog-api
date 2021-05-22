package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.BusinessException;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientCatalogService {

  @Autowired
  private ClientRepository clientRepository;

  @Transactional
  public Client save(Client client) {
    Boolean emailInUse = clientRepository.findByEmail(client.getEmail())
      .stream()
      .anyMatch(existingClient -> !existingClient.equals(client));
    
    if(emailInUse) {
      throw new BusinessException("Já existe um cliente cadastrado com este e-mail");
    }
    
    return clientRepository.save(client);
  }

  @Transactional
  public void delete(Long clientId) {
    clientRepository.deleteById(clientId);
  }

}
