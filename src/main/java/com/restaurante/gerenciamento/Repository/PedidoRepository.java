package com.restaurante.gerenciamento.Repository;

import com.restaurante.gerenciamento.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
