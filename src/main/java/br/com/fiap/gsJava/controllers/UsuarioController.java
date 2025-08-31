package br.com.fiap.gsJava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gsJava.dtos.usuario.UsuarioDTO;
import br.com.fiap.gsJava.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.gsJava.entities.Usuario;
import br.com.fiap.gsJava.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
@Tag(name = "Usuario", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> list = usuarioService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(
        @Parameter(description = "ID do usuário") @PathVariable Long id) {
        UsuarioDTO dto = usuarioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> save(
        @RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioDTO dto = usuarioService.save(usuarioRequestDTO);
        return ResponseEntity.created(null).body(dto);
    }

    @Operation(summary = "Atualizar um usuário", description = "Atualiza os dados de um usuário existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> update(
        @Parameter(description = "ID do usuário") @PathVariable Long id,
        @RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioDTO usuarioAtualizado = usuarioService.update(id, usuarioRequestDTO);
        return ResponseEntity.ok().body(usuarioAtualizado);
    }

    @Operation(summary = "Deletar um usuário", description = "Remove um usuário do sistema pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Usuario> deleteById(
        @Parameter(description = "ID do usuário") @PathVariable Long id) {
        Usuario usuario = usuarioService.deleteById(id);
        return ResponseEntity.ok().body(usuario);
    }
}