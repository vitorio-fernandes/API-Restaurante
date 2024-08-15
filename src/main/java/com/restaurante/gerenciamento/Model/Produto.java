package com.restaurante.gerenciamento.Model;

import java.util.List;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PRODUTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
  @Id
  @GeneratedValue
  private UUID id;
  @NotBlank
  private String nome;
  @NotBlank
  private String descricao;
  @NotNull
  private double preco;
  @Column(nullable = false)
  private boolean disponivel;
  @ManyToMany(mappedBy = "produtos")
  private List<Pedido> pedidos;
  
  public Produto(@NotBlank String nome, @NotBlank String descricao, @NotNull double preco, boolean disponivel) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.disponivel = disponivel;
  }
  
}
