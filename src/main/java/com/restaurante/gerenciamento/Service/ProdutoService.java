package com.restaurante.gerenciamento.Service;

import java.util.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.gerenciamento.DTO.Produto.ProdutoRequestDTO;
import com.restaurante.gerenciamento.Model.Produto;
import com.restaurante.gerenciamento.Repository.ProdutoRepository;

@Service
public class ProdutoService {
  @Autowired
  ProdutoRepository produtoRepository;

  // Criar Produto
  public Produto salvarProduto(ProdutoRequestDTO productDTO) {
    Produto produto = new Produto(productDTO.nome(), productDTO.descricao(), productDTO.preco(),
        productDTO.disponivel());
    return produtoRepository.save(produto);
  }

  // Atualizar Produto
  public Produto atualizarProduto(ProdutoRequestDTO produtoDTO, UUID id) {
    Optional<Produto> optionalProduto = produtoRepository.findById(id);
    if (optionalProduto.isPresent()) {
      Produto produtoExistente = optionalProduto.get();
      produtoExistente.setNome(produtoDTO.nome());
      produtoExistente.setDescricao(produtoDTO.descricao());
      produtoExistente.setPreco(produtoDTO.preco());
      produtoExistente.setDisponivel(produtoDTO.disponivel());
      return produtoRepository.save(produtoExistente);
    } else {
      // Caso não encontre o produto
      throw new RuntimeException("Produto não encontrado");
    }
  }

  // Remover Produto
  public void deletarProduto(UUID id) {
    produtoRepository.deleteById(id);
  }

  // Listar TODOS os produtos
  public List<Produto> listarTodosProdutos() {
    return produtoRepository.findAll();
  }

  // Listar os produtos que NÃO estão disponivéis
  public List<Produto> produtosNaoDisponiveis() {
    return produtoRepository.findByDisponivelFalse();
  }

  // Pesquisar Produto pelo seu ID
  public Produto produtoPeloId(UUID id) {
    Optional<Produto> optProdutos = produtoRepository.findById(id);
    if (optProdutos.isPresent()) {
      Produto produto = optProdutos.get();
      return produto;
    } else {
      throw new RuntimeException("Produto não encontrado");
    }
  }
}
