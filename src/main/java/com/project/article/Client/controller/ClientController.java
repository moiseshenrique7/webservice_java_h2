package com.project.article.Client.controller;

import com.project.article.Client.entity.Client;
import com.project.article.Client.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client){
        return clientService.save(client);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> clientList(){
        return clientService.clientList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client searchClientById(@PathVariable("id") Long id){
        return clientService.searchById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClientById(@PathVariable("id") Long id){
        clientService.searchById(id)
                .map(client -> {
                    clientService.removeById(client.getId());
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@PathVariable("id") Long id, @RequestBody Client client){
        clientService.searchById(id)
                .map(clientBase -> {
                    modelMapper.map(client, clientBase);
                    clientService.save(clientBase);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
}
