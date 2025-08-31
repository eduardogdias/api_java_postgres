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

import br.com.fiap.gsJava.dtos.emergencia.EmergenciaRequestDTO;
import br.com.fiap.gsJava.dtos.emergencia.EmergenciaRequestPutDTO;
import br.com.fiap.gsJava.dtos.emergencia.EmergenciaResponseDTO;
import br.com.fiap.gsJava.services.EmergenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/emergencias")
@Tag(name = "Emergencia", description = "Endpoints para gerenciamento de emergências")
public class EmergenciaController {

    @Autowired
    private EmergenciaService emergenciaService;

    @Operation(summary = "Listar todas as emergências", description = "Retorna uma lista com todas as emergências registradas.")
    @ApiResponse(responseCode = "200", description = "Lista de emergências retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<EmergenciaResponseDTO>> findAll() {
        List<EmergenciaResponseDTO> list = emergenciaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar emergência por ID", description = "Retorna os dados de uma emergência específica pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Emergência encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Emergência não encontrada")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmergenciaResponseDTO> findById(
        @Parameter(description = "ID da emergência") @PathVariable Long id) {
        EmergenciaResponseDTO emergencia = emergenciaService.findById(id);
        return ResponseEntity.ok().body(emergencia);
    }

    @Operation(summary = "Criar uma nova emergência", description = "Cria uma nova emergência com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Emergência criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    })
    @PostMapping
    public ResponseEntity<EmergenciaResponseDTO> save(
        @RequestBody @Valid EmergenciaRequestDTO emergenciaDTO) {
        EmergenciaResponseDTO result = emergenciaService.save(emergenciaDTO);
        return ResponseEntity.created(null).body(result);
    }

    @Operation(summary = "Atualizar uma emergência", description = "Atualiza os dados de uma emergência existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Emergência atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Emergência não encontrada")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<EmergenciaResponseDTO> update(
        @Parameter(description = "ID da emergência") @PathVariable Long id,
        @RequestBody @Valid EmergenciaRequestPutDTO emergenciaDTO) {
        EmergenciaResponseDTO emergenciaAtualizada = emergenciaService.update(id, emergenciaDTO);
        return ResponseEntity.ok().body(emergenciaAtualizada);
    }

    @Operation(summary = "Deletar uma emergência", description = "Remove uma emergência do sistema pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Emergência deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Emergência não encontrada")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EmergenciaResponseDTO> deleteById(
        @Parameter(description = "ID da emergência") @PathVariable Long id) {
        EmergenciaResponseDTO emergencia = emergenciaService.deleteById(id);
        return ResponseEntity.ok().body(emergencia);
    }
}
