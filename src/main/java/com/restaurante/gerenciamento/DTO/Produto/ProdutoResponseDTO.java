package com.restaurante.gerenciamento.DTO.Produto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoResponseDTO(
  UUID id,  
  @NotBlank String nome,
  @NotBlank String descricao,
  @NotNull double preco,
  boolean disponivel
) {}
