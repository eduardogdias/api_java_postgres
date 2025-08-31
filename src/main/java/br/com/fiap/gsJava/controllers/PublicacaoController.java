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

import br.com.fiap.gsJava.dtos.publicacao.PublicacaoRequestDTO;
import br.com.fiap.gsJava.dtos.publicacao.PublicacaoRequestPutDTO;
import br.com.fiap.gsJava.dtos.publicacao.PublicacaoResponseDTO;
import br.com.fiap.gsJava.services.PublicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/publicacoes")
@Tag(name = "Publicacao", description = "Endpoints para gerenciamento de publicações feitas por usuários")
public class PublicacaoController {

    @Autowired
    private PublicacaoService publicacaoService;

    @Operation(summary = "Listar todas as publicações", description = "Retorna todas as publicações ou apenas as ativas/inativas se o status for informado.")
    @ApiResponse(responseCode = "200", description = "Lista de publicações retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<PublicacaoResponseDTO>> findAll(
        @Parameter(description = "Filtrar por status da publicação (true = ativas, false = inativas)") 
        @RequestParam(required = false) Boolean status) {

        List<PublicacaoResponseDTO> listDto;

        if (status == null) {
            listDto = publicacaoService.findAll();
        } else {
            listDto = publicacaoService.findAllByStatus(status);
        }

        return ResponseEntity.ok().body(listDto);
    }

    @Operation(summary = "Buscar publicação por ID", description = "Retorna os dados de uma publicação específica pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publicação encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<PublicacaoResponseDTO> findById(
        @Parameter(description = "ID da publicação") @PathVariable Long id) {
        PublicacaoResponseDTO dto = publicacaoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Criar uma nova publicação", description = "Cadastra uma nova publicação com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Publicação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    })
    @PostMapping
    public ResponseEntity<PublicacaoResponseDTO> save(
        @RequestBody @Valid PublicacaoRequestDTO publicacaoRequestDTO) {
        PublicacaoResponseDTO dto = publicacaoService.save(publicacaoRequestDTO);
        return ResponseEntity.created(null).body(dto);
    }

    @Operation(summary = "Atualizar uma publicação", description = "Atualiza os dados de uma publicação existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publicação atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<PublicacaoResponseDTO> update(
        @Parameter(description = "ID da publicação") @PathVariable Long id,
        @RequestBody @Valid PublicacaoRequestPutDTO publicacaoRequestPutDTO) {
        PublicacaoResponseDTO dto = publicacaoService.update(id, publicacaoRequestPutDTO);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Deletar uma publicação", description = "Remove uma publicação do sistema pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publicação deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PublicacaoResponseDTO> deleteById(
        @Parameter(description = "ID da publicação") @PathVariable Long id) {
        PublicacaoResponseDTO dto = publicacaoService.deleteById(id);
        return ResponseEntity.ok().body(dto);
    }
}