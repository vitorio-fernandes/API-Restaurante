package com.restaurante.gerenciamento.Model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
  @Size(min = 5)
  private String login;
  @NotBlank
  @Size(min = 8)
  private String senha;
  public Administrador(@Size(min = 5) String login, @NotBlank @Size(min = 8) String senha) {
    this.login = login;
    this.senha = senha;
  }
}
