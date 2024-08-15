package com.restaurante.gerenciamento.DTO.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDTO(
  @NotBlank String nome,
  @NotBlank String descricao,
  @NotNull double preco,
  boolean disponivel
) {}
