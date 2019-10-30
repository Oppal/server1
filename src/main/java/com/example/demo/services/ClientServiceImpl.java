package com.example.demo.services;

import com.example.demo.UnauthorizedException;
import com.example.demo.models.Client;
import com.example.demo.models.ClientType;
import com.example.demo.repositories.ClientRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    public ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client create(Client client) {
        Optional<Client> exist = findByClientId(client.getClientId());
        return exist.orElseGet(() -> clientRepository.save(client));
    }

    @Override
    public Client findByID(Long id) throws NotFoundException {
        return clientRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User " + id + " Not Found"));
    }

    @Override
    public Optional<Client> findByClientId(int id) {
        return clientRepository.findByClientId(id);
    }

    @Override
    public Client update(Long id, Client client) throws NotFoundException {
        Client clientUpdate = findByID(id);
        if (clientUpdate.getClientType() != ClientType.admin){
            if (clientUpdate.getClientId() != client.getClientId())
                throw new NotFoundException("Client " + id + " with id " + client.getClientId() + " does not exist");

            clientUpdate.setClientName(client.getClientName());
            return clientRepository.save(clientUpdate);
        }
        throw new UnauthorizedException("not authorized to modify this entity");

    }

    @Override
    public void delete(Long id, Client client) throws NotFoundException {
        Client clientDelete = findByID(id);

        if (clientDelete.getClientId() != client.getClientId())
            throw new NotFoundException("Client " + id + " with National Id " + client.getClientId() + " does not exist");

        clientRepository.deleteById(id);
    }
}