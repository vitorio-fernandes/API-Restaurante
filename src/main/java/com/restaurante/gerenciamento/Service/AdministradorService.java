package com.restaurante.gerenciamento.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.gerenciamento.DTO.Administrador.AdministradorRequestDTO;
import com.restaurante.gerenciamento.Model.Administrador;
import com.restaurante.gerenciamento.Repository.AdministradorRepository;
import java.util.UUID;
@Service
public class AdministradorService {
  @Autowired
  AdministradorRepository repository;
  
 // Cadastrar outro Administrador
  public Administrador criarAdm(AdministradorRequestDTO requestDTO){
    Administrador administrador = new Administrador(requestDTO.login(), requestDTO.senha());
    return repository.save(administrador);
  }
// Excluir outro Administrador
  public void excluirAdm(UUID id){
    Administrador administrador = repository.findById(id).orElseThrow(()-> new RuntimeException("Administrador não encontrado"));
    repository.delete(administrador);
  }

  public Administrador atualizarAdm(UUID id, AdministradorRequestDTO requestDTO){
    Administrador administrador = repository.findById(id).orElseThrow(()-> new RuntimeException("Administrador não encontrado"));
    administrador.setLogin(requestDTO.login());
    administrador.setSenha(requestDTO.senha());
    return repository.save(administrador);
  }

}
