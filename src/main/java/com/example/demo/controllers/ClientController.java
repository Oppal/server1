package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.ClientService;
import com.example.demo.services.MovieService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "clients")

public class ClientController {

    private final ClientService clientService;
    private final MovieService movieService;

    public ClientController(ClientService clientService, MovieService movieService) {
        this.clientService = clientService;
        this.movieService = movieService;
    }

    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping(value = "{id}")
    Client findById(@PathVariable Long id) throws NotFoundException {
        return clientService.findByID(id);
    }

    //create a user
    @PostMapping
    Client create(@Valid @RequestBody Client client)
    {
        client.setClientType(ClientType.user);
        return clientService.create(client);
    }

    //create a movie
    @PostMapping(value = "{id}/movie")
    Movie create(@Valid @RequestBody Movie movie, @PathVariable Long id) throws NotFoundException {
        Client client = findById(id);
        movie.setClient(client);

        if (client.getClientType() == ClientType.admin){
            movie.setMovieType(MovieType.original);

        }else {
            movie.setMovieType(MovieType.suggested);
        }
        return movieService.create(movie);
    }

    //update a movie
    @PatchMapping(value = "{clientid}/movie/{id}")
    Movie update(@PathVariable(value = "id") Long id, @PathVariable(value = "clientid") Long clientid, @RequestBody Movie movie) throws NotFoundException {
        return movieService.update(id, clientid, movie);
    }

    //delete a movie
    @DeleteMapping(value = "{clientid}/movie/{id}")
    String deleteMovie(@PathVariable(value = "id") Long id, @PathVariable(value = "clientid") Long clientid) throws NotFoundException {
        return movieService.delete(id, clientid);
    }

    //delete a user
    @DeleteMapping(value = "{id}")
    void delete(@PathVariable Long id, @RequestBody Client client) throws NotFoundException {
        clientService.delete(id, client);
    }

    //update a user
    @PatchMapping(value = "{id}")
    Client update(@PathVariable Long id, @RequestBody Client client) throws NotFoundException {
        return clientService.update(id, client);
    }

}