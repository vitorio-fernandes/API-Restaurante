package com.restaurante.gerenciamento.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.restaurante.gerenciamento.DTO.Cliente.ClienteRequestDTO;
import com.restaurante.gerenciamento.Model.Cliente;
import com.restaurante.gerenciamento.Repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    // Cadastrar Cliente
    public Cliente cadastroCliente(ClienteRequestDTO requestDTO) {
        Cliente cliente = new Cliente(requestDTO.nome(), requestDTO.endereco(), requestDTO.email());
        return clienteRepository.save(cliente);
    }

    // Atualizar Cliente pelo ID
    public Cliente atualizarCliente(ClienteRequestDTO requestDTO, UUID id) {
       Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
            clienteExistente.setNome(requestDTO.nome());
            clienteExistente.setEmail(requestDTO.email());
            clienteExistente.setEndereco(requestDTO.endereco());
            return clienteRepository.save(clienteExistente);
    }

    // Remover Cliente pelo ID
    public void removerCliente(UUID id) {
        Optional<Cliente> optCliente = clienteRepository.findById(id);
        if (optCliente.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    // Listar todos os Clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Procurar Cliente pelo ID
    public Cliente procurarClientePorId(UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

}
