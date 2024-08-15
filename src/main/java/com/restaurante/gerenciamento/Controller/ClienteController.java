package com.restaurante.gerenciamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.gerenciamento.DTO.Cliente.ClienteRequestDTO;
import com.restaurante.gerenciamento.DTO.Cliente.ClienteResponseDTO;
import com.restaurante.gerenciamento.DTO.Pedido.PedidoRequestDTO;
import com.restaurante.gerenciamento.DTO.Pedido.PedidoResponseDTO;
import com.restaurante.gerenciamento.DTO.Pedido.PedidoStatusRequestDTO;
import com.restaurante.gerenciamento.DTO.Produto.ProdutoResponseDTO;
import com.restaurante.gerenciamento.Model.Cliente;
import com.restaurante.gerenciamento.Model.Pedido;
import com.restaurante.gerenciamento.Model.Produto;
import com.restaurante.gerenciamento.Service.ClienteService;
import com.restaurante.gerenciamento.Service.PedidoService;
import com.restaurante.gerenciamento.Service.ProdutoService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
  
  @Autowired
  ClienteService clienteService;
  @Autowired
  ProdutoService produtoService;
  @Autowired
  PedidoService pedidoService;
  //cliente deve poder atualizar suas informações de contato. OK
  @PutMapping("/{id}")
  public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable UUID id, @RequestBody ClienteRequestDTO requestDTO){
    Cliente cliente = clienteService.atualizarCliente(requestDTO, id);
    ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(id, cliente.getNome(), cliente.getEndereco(), cliente.getEmail());
    return ResponseEntity.ok(clienteResponseDTO);
  }
  
  //O cliente deve poder visualizar os produtos disponíveis. OK
  @GetMapping
  public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos(){
    List<Produto> produtos = produtoService.produtosDisponiveis();
    List<ProdutoResponseDTO> produtosResponse = new ArrayList<>();
    for (Produto produto : produtos) {
      ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.isDisponivel());
      produtosResponse.add(produtoResponseDTO);
    }
    return ResponseEntity.ok(produtosResponse);
  }

  //O cliente deve poder realizar um pedido selecionando os produtos desejados.
  @PostMapping
  public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody PedidoRequestDTO requestDTO) {
    Pedido pedido = pedidoService.criarPedido(requestDTO);
    PedidoResponseDTO pedidoResponse = new PedidoResponseDTO(pedido.getId(), pedido.getCliente().getId(), pedido.getValorTotal(), pedido.getStatus(),
     pedido.getProdutos());
    return ResponseEntity.ok(pedidoResponse);
  }
   
  // O cliente deve poder cancelar um pedido em andamento
  @PutMapping("cancelar-pedido/{id}")
  public ResponseEntity<PedidoResponseDTO> cancelarPedido(@PathVariable UUID id, @RequestBody PedidoStatusRequestDTO requestDTO) {
    Pedido pedido = pedidoService.cancelarPedido(requestDTO, id);
    PedidoResponseDTO responseDTO = new PedidoResponseDTO(id, pedido.getCliente().getId(), pedido.getValorTotal(), pedido.getStatus(), pedido.getProdutos());
    return ResponseEntity.ok(responseDTO);

  }
  
}
