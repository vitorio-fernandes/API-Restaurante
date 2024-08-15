package com.restaurante.gerenciamento.Model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_CLIENTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {
  @Id
  @GeneratedValue
  private UUID id;
  @Column(nullable = false)
  private String nome;
  @Column(nullable = false)
  private String endereco;
  @Email
  @Column(unique = true, nullable = false)
  private String email;

  @OneToMany(mappedBy = "cliente")
  private List<Pedido> pedidos;

  public Cliente(String nome, String endereco, @Email String email) {
    this.nome = nome;
    this.endereco = endereco;
    this.email = email;
  }
}
