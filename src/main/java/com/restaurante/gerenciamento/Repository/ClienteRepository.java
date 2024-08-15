package com.restaurante.gerenciamento.Repository;

import com.restaurante.gerenciamento.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
