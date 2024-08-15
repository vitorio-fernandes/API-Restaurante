package com.restaurante.gerenciamento.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.gerenciamento.DTO.Cliente.ClienteRequestDTO;
import com.restaurante.gerenciamento.DTO.Cliente.ClienteResponseDTO;
import com.restaurante.gerenciamento.DTO.Pedido.PedidoStatusRequestDTO;
import com.restaurante.gerenciamento.DTO.Pedido.PedidoStatusResponseDTO;
import com.restaurante.gerenciamento.DTO.Produto.ProdutoRequestDTO;
import com.restaurante.gerenciamento.DTO.Produto.ProdutoResponseDTO;
import com.restaurante.gerenciamento.Model.Cliente;
import com.restaurante.gerenciamento.Model.Pedido;
import com.restaurante.gerenciamento.Model.Produto;
import com.restaurante.gerenciamento.Service.ClienteService;
import com.restaurante.gerenciamento.Service.PedidoService;
import com.restaurante.gerenciamento.Service.ProdutoService;

@RestController
@RequestMapping("/api/admin")
public class AdministradorController {
  @Autowired
  ClienteService clienteService;
  @Autowired
  PedidoService pedidoService;
  @Autowired
  ProdutoService produtoService;

  /*
   * O sistema deve permitir o cadastro de clientes com nome, endereço, telefone e
   * email. OK
   * O sistema deve permitir o cadastro de produtos com nome, descrição, preço e
   * disponibilidade. OK
   * O sistema deve calcular o valor total do pedido automaticamente. OK
   * O sistema deve permitir ao administrador atualizar o status do pedido. OK
   */
  @PostMapping("/cliente")
  public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody ClienteRequestDTO requestDTO) {
    Cliente cliente = clienteService.cadastroCliente(requestDTO);
    ClienteResponseDTO responseDTO = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEndereco(),
        cliente.getEmail());
    return ResponseEntity.ok(responseDTO);
  }

  @PostMapping("/produto")
  public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequestDTO requestDTO) {
    Produto produto = produtoService.salvarProduto(requestDTO);
    ProdutoResponseDTO responseDTO = new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getDescricao(),
        produto.getPreco(), produto.isDisponivel());
    return ResponseEntity.ok(responseDTO);
  }

  @PutMapping("pedido/{id}") // Atualizar o Status do Pedido
  public ResponseEntity<PedidoStatusResponseDTO> atualizarStatusPedido(@PathVariable UUID id,
      @RequestBody PedidoStatusRequestDTO requestDTO) {
    Pedido pedido = pedidoService.atualizarStatusPedido(requestDTO, id);
    PedidoStatusResponseDTO responseDTO = new PedidoStatusResponseDTO(id, pedido.getStatus());
    return ResponseEntity.ok(responseDTO);
  }
}
