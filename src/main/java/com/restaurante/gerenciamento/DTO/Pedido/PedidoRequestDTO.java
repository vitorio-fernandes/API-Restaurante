package com.restaurante.gerenciamento.DTO.Pedido;

import java.util.List;
import java.util.UUID;


public record PedidoRequestDTO(
  UUID clienteId,
  List<UUID> produtoIds
) {}
