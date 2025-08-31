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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gsJava.dtos.local.LocalRequestDTO;
import br.com.fiap.gsJava.dtos.local.LocalResponseDTO;
import br.com.fiap.gsJava.services.LocalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/locais")
@Tag(name = "Local", description = "Endpoints para gerenciamento de locais")
public class LocalController {

    @Autowired
    private LocalService localService;

    @Operation(
        summary = "Listar todos os locais",
        description = "Retorna uma lista de todos os locais. É possível filtrar por status ativo/inativo."
    )
    @ApiResponse(responseCode = "200", description = "Lista de locais retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<LocalResponseDTO>> findAll(
        @Parameter(description = "Filtrar locais por status (true para ativos, false para inativos). Opcional.")
        @RequestParam(required = false) Boolean status) {

        List<LocalResponseDTO> list;

        if (status == null) {
            list = localService.findAll();
        } else {
            list = localService.findByStatus(status);
        }

        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar local por ID", description = "Retorna os dados de um local específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Local encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<LocalResponseDTO> findById(
        @Parameter(description = "ID do local") @PathVariable Long id) {
        LocalResponseDTO local = localService.findById(id);
        return ResponseEntity.ok().body(local);
    }

    @Operation(summary = "Criar um novo local", description = "Cria um novo local com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Local criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    })
    @PostMapping
    public ResponseEntity<LocalResponseDTO> save(
        @RequestBody @Valid LocalRequestDTO localDTO) {
        LocalResponseDTO result = localService.save(localDTO);
        return ResponseEntity.created(null).body(result);
    }

    @Operation(summary = "Atualizar um local", description = "Atualiza os dados de um local existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Local atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<LocalResponseDTO> update(
        @Parameter(description = "ID do local") @PathVariable Long id,
        @RequestBody @Valid LocalRequestDTO localDTO) {
        LocalResponseDTO localAtualizado = localService.update(id, localDTO);
        return ResponseEntity.ok().body(localAtualizado);
    }

    @Operation(summary = "Deletar um local", description = "Remove um local do sistema pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Local deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<LocalResponseDTO> deleteById(
        @Parameter(description = "ID do local") @PathVariable Long id) {
        LocalResponseDTO local = localService.deleteById(id);
        return ResponseEntity.ok().body(local);
    }
}