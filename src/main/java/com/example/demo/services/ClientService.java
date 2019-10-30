package com.example.demo.services;

import com.example.demo.models.Client;
import com.example.demo.repositories.ClientRepository;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientRepository CLIENT_REPOSITORY = null;

    List<Client> findAll();

    public Client create(Client Client);

    Client findByID(Long id) throws NotFoundException;

    Optional<Client> findByClientId(int id);

    public Client update(Long id, Client Client) throws NotFoundException;

    public void delete(Long id, Client client) throws NotFoundException;

}