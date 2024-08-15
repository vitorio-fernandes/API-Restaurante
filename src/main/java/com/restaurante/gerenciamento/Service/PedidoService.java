package com.restaurante.gerenciamento.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.restaurante.gerenciamento.DTO.Pedido.PedidoRequestDTO;
import com.restaurante.gerenciamento.DTO.Pedido.PedidoStatusRequestDTO;
import com.restaurante.gerenciamento.Model.Cliente;
import com.restaurante.gerenciamento.Model.Pedido;
import com.restaurante.gerenciamento.Model.Produto;
import com.restaurante.gerenciamento.Model.StatusPedido;
import com.restaurante.gerenciamento.Repository.ClienteRepository;
import com.restaurante.gerenciamento.Repository.PedidoRepository;
import com.restaurante.gerenciamento.Repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
  @Autowired
  private PedidoRepository pedidoRepository;
  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private ProdutoRepository produtoRepository;

  // Criar Pedido
  @Transactional
  public Pedido criarPedido(PedidoRequestDTO requestDTO) {
    // Ver se o cliente existe
    Cliente cliente = clienteRepository.findById(requestDTO.clienteId())
        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    // Verificar os ID's dos Produtos
    List<Produto> produtos = new ArrayList<>();
    for (UUID produtoId : requestDTO.produtoIds()) {
      Produto produto = produtoRepository.findById(produtoId)
          .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
      produtos.add(produto);
    }
    double valorTotal = calcularValorTotal(produtos); // Calcular o valor do Pedido após a lista de Produtos
    // Criar o pedido
    Pedido pedido = new Pedido();
    pedido.setCliente(cliente);
    pedido.setProdutos(produtos);
    pedido.setStatus(StatusPedido.EM_ANDAMENTO); // Já inicia o pedido em andamento
    pedido.setValorTotal(valorTotal);
    return pedidoRepository.save(pedido);
  }

  // Deletar Pedido
  @Transactional
  public void deletarPedido(UUID pedidoId) {
    Pedido pedido = pedidoRepository.findById(pedidoId)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    pedidoRepository.delete(pedido);
  }
  // Atualizar Status do Pedido
  @Transactional
  public Pedido atualizarStatusPedido(PedidoStatusRequestDTO dto, UUID id_pedido){
    Pedido pedido = pedidoRepository.findById(id_pedido).orElseThrow(()-> new IllegalArgumentException("Pedido não encontrado"));
    pedido.setStatus(dto.statusPedido());
    return pedidoRepository.save(pedido);
  }

  @Transactional
public Pedido cancelarPedido(PedidoStatusRequestDTO dto, UUID id_pedido) {
    Pedido pedido = pedidoRepository.findById(id_pedido).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

    if (pedido.getStatus().equals(StatusPedido.CONCLUIDO)) {
        throw new IllegalStateException("Não é possível modificar um pedido que está concluído.");
    }
    // Verifica se o cliente está tentando definir um status não permitido
    if (dto.statusPedido().equals(StatusPedido.CONCLUIDO) || dto.statusPedido().equals(StatusPedido.EM_ANDAMENTO)) {
        throw new IllegalArgumentException("Você não tem permissão para definir o pedido como CONCLUÍDO ou EM ANDAMENTO.");
    }
    // Atualiza o status do pedido para CANCELADO
    pedido.setStatus(dto.statusPedido());

    return pedidoRepository.save(pedido);
}

  // Atualizar pedido
  @Transactional
  public Pedido atualizarPedido(PedidoRequestDTO requestDTO, UUID id_pedido) {
    // Verifica se o Pedido Existe
    Pedido pedido = pedidoRepository.findById(id_pedido)
        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    // Verificar o ID cliente
    Cliente cliente = clienteRepository.findById(requestDTO.clienteId())
        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    // Verificar Produtos
    List<Produto> produtos = new ArrayList<>();
    for (UUID produtoID : requestDTO.produtoIds()) {
      Produto produto = produtoRepository.findById(produtoID)
          .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
      produtos.add(produto);
    }
    double valorTotal = calcularValorTotal(produtos); // Calcula valor final do Pedido
    // Atualizar Pedido
    pedido.setCliente(cliente);
    pedido.setProdutos(produtos);
    pedido.setStatus(StatusPedido.EM_ANDAMENTO);
    pedido.setValorTotal(valorTotal);

    return pedidoRepository.save(pedido);
  }

  // Listar todos Pedidos
  @Transactional
  public List<Pedido> listarPedidos() {
    return pedidoRepository.findAll();
  }

  // Pedido pelo ID
  @Transactional
  public Pedido buscarPedidoPorId(UUID pedidoId) {
    return pedidoRepository.findById(pedidoId).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
  }

  // Pedidos especificos de um cliente
  public List<Pedido> pedidosCliente(UUID id_cliente) {
    Cliente cliente = clienteRepository.findById(id_cliente)
        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    return cliente.getPedidos();
  }

  // Calcular o valor total do Pedido
  public double calcularValorTotal(List<Produto> produtos) {
    double valorTotal = 0.0;
    for (Produto produto : produtos) {
      valorTotal += produto.getPreco();
    }
    return valorTotal;
  }
}
