package com.restaurante.gerenciamento.Repository;

import com.restaurante.gerenciamento.Model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdministradorRepository extends JpaRepository<Administrador, UUID> {
}
