package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;


    @Column(name = "clientName")
    private String clientName;


    @Column(name = "clientType")
    private ClientType clientType;


    @Column(name = "clientId", unique = true)
    private int clientId;



    public Client() {
    }

    public Client(@NotNull(message = "Missing client name. please specify") String clientName, @NotNull(message = "missing client type. please specify") ClientType clientType, @NotNull(message = "Your client id is required") int clientId) {
        this.clientName = clientName;
        this.clientType = clientType;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}