package com.restaurante.gerenciamento.DTO.Pedido;

import java.util.List;
import java.util.UUID;

import com.restaurante.gerenciamento.Model.Produto;
import com.restaurante.gerenciamento.Model.StatusPedido;

public record PedidoResponseDTO(
  UUID id,
  UUID clienteId,
  double valorTotal,
  StatusPedido statusPedido,
  List<Produto> produtos
) {
  
}
