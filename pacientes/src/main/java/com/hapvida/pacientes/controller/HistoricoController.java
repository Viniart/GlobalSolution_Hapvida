package com.hapvida.pacientes.controller;

import com.hapvida.pacientes.model.Historico;
import com.hapvida.pacientes.model.Paciente;
import com.hapvida.pacientes.service.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/historico")
public class HistoricoController {
    private HistoricoService service;

    private KafkaTemplate<String, Paciente> kafkaTemplate;

    public HistoricoController(HistoricoService service, KafkaTemplate<String, Paciente> kafkaTemplate) {
        this.service = service;
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping
    @Operation(summary = "Retorna a lista de historicos", description = "Método que acessa o método listarTodos do service para exibir a lista de historicos")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso"
    )
    public ResponseEntity listarHistoricos() {
        return new ResponseEntity(service.listarTodos(), HttpStatus.OK);
    }

    @PostMapping(value = "/novo")
    @Operation(summary = "Cadastra um novo historico", description = "Método que acessa o método cadastrarHistorico do service passando um historico a ser cadastrado")
    @ApiResponse(
            responseCode = "201",
            description = "Cadastro bem sucedido!"
    )
    public ResponseEntity cadastrarHistorico(@RequestBody Historico historico) {

        service.cadastrarHistorico(historico);
//        kafkaTemplate.send("pacientes", paciente);
        return new ResponseEntity("Cadastrado com Sucesso!", HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Exclui um historico", description = "Método que acessa o método deletarHistorico do service passando o id para a remover da lista")
    @ApiResponse(
            responseCode = "200",
            description = "Exclusão bem sucedida!"
    )
    @ApiResponse(
            responseCode = "400",
            description = "ID não encontrado!"
    )
    public ResponseEntity excluirHistorico(@PathVariable Integer id) {

        try {
            service.deletarHistorico(id);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity("ID Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Excluído com Sucesso", HttpStatus.OK);

    }

    @PutMapping(value = "/editar/{id}")
    @Operation(summary = "Altera um historico", description = "Método que acessa o método alterarHistorico do service passando o id para atualizar a lista")
    @ApiResponse(
            responseCode = "200",
            description = "Alteração bem sucedida!"
    )
    @ApiResponse(
            responseCode = "400",
            description = "ID não encontrado!"
    )
    public ResponseEntity alterarHistorico(@PathVariable Integer id, @RequestBody Historico historico) {

        try {
            service.editarHistorico(id, historico);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity("ID Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Excluído com Sucesso", HttpStatus.OK);

    }
}
