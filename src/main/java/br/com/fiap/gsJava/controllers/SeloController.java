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

import br.com.fiap.gsJava.entities.Selo;
import br.com.fiap.gsJava.services.SeloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/selos")
@Tag(name = "Selo", description = "Endpoints para gerenciamento de selos")
public class SeloController {

    @Autowired
    private SeloService seloService;

    @Operation(summary = "Listar todos os selos", description = "Retorna uma lista com todos os selos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de selos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Selo>> findAll() {
        List<Selo> list = seloService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar selo por ID", description = "Retorna os dados de um selo específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Selo encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Selo não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Selo> findById(
        @Parameter(description = "ID do selo") @PathVariable Long id) {
        Selo selo = seloService.findById(id);
        return ResponseEntity.ok().body(selo);
    }

    @Operation(summary = "Criar um novo selo", description = "Cria um novo selo com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Selo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    })
    @PostMapping
    public ResponseEntity<Selo> save(
        @RequestBody @Valid Selo selo) {
        Selo result = seloService.save(selo);
        return ResponseEntity.created(null).body(result);
    }

    @Operation(summary = "Atualizar um selo", description = "Atualiza os dados de um selo existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Selo atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Selo não encontrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Selo> update(
        @Parameter(description = "ID do selo") @PathVariable Long id,
        @RequestBody @Valid Selo selo) {
        Selo seloAtualizado = seloService.update(id, selo);
        return ResponseEntity.ok().body(seloAtualizado);
    }

    @Operation(summary = "Deletar um selo", description = "Remove um selo do sistema pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Selo deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Selo não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Selo> deleteById(
        @Parameter(description = "ID do selo") @PathVariable Long id) {
        Selo selo = seloService.deleteById(id);
        return ResponseEntity.ok().body(selo);
    }
    
}
