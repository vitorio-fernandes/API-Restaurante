package com.restaurante.gerenciamento.DTO.Cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
  @NotBlank String nome,
  @NotBlank String endereco,
  @NotBlank String email
) {} 