package com.hapvida.analisedados.controller;

import com.hapvida.analisedados.model.Log;
import com.hapvida.analisedados.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/log")
public class LogController {
    private LogService service;

    private KafkaTemplate<String, String> kafkaTemplate;

    public LogController(LogService service, KafkaTemplate<String, String> kafkaTemplate) {
        this.service = service;
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping
    @Operation(summary = "Retorna todos os logs", description = "Método que acessa o método listarTodos do service para exibir os logs")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso"
    )
    public ResponseEntity listarLogs() {
        return new ResponseEntity(service.listarTodos(), HttpStatus.OK);
    }

    @PostMapping(value = "/novo")
    @Operation(summary = "Cadastra um novo historico", description = "Método que acessa o método cadastrarHistorico do service passando um historico a ser cadastrado")
    @ApiResponse(
            responseCode = "201",
            description = "Cadastro bem sucedido!"
    )
    public ResponseEntity cadastrarLog(@RequestBody Log log) {

        service.cadastrarLog(log);
//        kafkaTemplate.send("pacientes", paciente);
        return new ResponseEntity("Cadastrado com Sucesso!", HttpStatus.CREATED);

    }
}
