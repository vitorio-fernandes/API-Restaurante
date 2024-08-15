package com.restaurante.gerenciamento.DTO.Cliente;


import java.util.UUID;

public record ClienteResponseDTO(
  UUID id,
  String nome,
  String endereco,
  String email
  //List<PedidoResponseDTO> pedidos
) {}
