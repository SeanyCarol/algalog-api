package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import com.algaworks.algalog.domain.model.Client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  @GetMapping("/clientes")
  public List<Client> listar() {
    var client1 = new Client();
    client1.setId(1L);
    client1.setName("João");
    client1.setEmail("joão@algaworks.com");
    client1.setTelephone("32 99999-1111");

    var client2 = new Client();
    client2.setId(2L);
    client2.setName("Maria");
    client2.setEmail("maria@algaworks.com");
    client2.setTelephone("32 98888-2222");

    return Arrays.asList(client1, client2);
  }
}
