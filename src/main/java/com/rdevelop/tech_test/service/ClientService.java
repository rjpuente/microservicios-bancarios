package com.rdevelop.tech_test.service;

import com.rdevelop.tech_test.entity.Cliente;
import com.rdevelop.tech_test.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClientService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAllClients() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findByClientId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteClient(Long id) {
        clienteRepository.deleteById(id);
    }
}
