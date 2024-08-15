package com.restaurante.gerenciamento.Model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ADMINISTRADOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {
  @Id
  @GeneratedValue
  private UUID id;
  @Column(unique = true, nullable = false)
  private String login;
  @NotBlank
  private String senha;
}
