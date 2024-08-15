package com.restaurante.gerenciamento.DTO.Pedido;


import java.util.UUID;
import com.restaurante.gerenciamento.Model.StatusPedido;

public record PedidoStatusResponseDTO(
  UUID PedidoId,
  StatusPedido statusPedido
) {}
