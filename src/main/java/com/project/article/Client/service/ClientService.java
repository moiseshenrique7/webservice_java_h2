package com.project.article.Client.service;

import com.project.article.Client.entity.Client;
import com.project.article.Client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public List<Client> clientList(){
        return clientRepository.findAll();
    }

    public Optional<Client> searchById(Long id){
        return clientRepository.findById(id);
    }

    public void removeById(Long id){
        clientRepository.deleteById(id);
    }
}
