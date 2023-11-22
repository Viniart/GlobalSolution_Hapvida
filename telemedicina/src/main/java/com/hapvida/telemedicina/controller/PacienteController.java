package com.hapvida.telemedicina.controller;

import com.hapvida.telemedicina.model.Consulta;
import com.hapvida.telemedicina.model.Paciente;
import com.hapvida.telemedicina.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/paciente")
public class PacienteController {
    private PacienteService service;

    private KafkaTemplate<String, Consulta> kafkaTemplate;

    public PacienteController(PacienteService service, KafkaTemplate<String, Consulta> kafkaTemplate) {
        this.service = service;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping
    @Operation(summary = "Retorna a lista de pacientes", description = "Método que acessa o método listarTodos do service para exibir a lista de pacientes")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso"
    )
    public ResponseEntity listarPacientes() {
        return new ResponseEntity(service.listarTodos(), HttpStatus.OK);
    }

    @PostMapping(value = "/novo")
    @Operation(summary = "Cadastra um novo paciente", description = "Método que acessa o método cadastrarPaciente do service passando um paciente a ser cadastrado")
    @ApiResponse(
            responseCode = "201",
            description = "Cadastro bem sucedido!"
    )
    public ResponseEntity cadastrarPaciente(@RequestBody Paciente paciente) {

        service.cadastrarPaciente(paciente);
//        kafkaTemplate.send("pacientes", paciente.getCpf());
        return new ResponseEntity("Cadastrado com Sucesso!", HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/deletar/{cpf}")
    @Operation(summary = "Exclui um paciente", description = "Método que acessa o método deletarPaciente do service passando o cpf para a remover da lista")
    @ApiResponse(
            responseCode = "200",
            description = "Exclusão bem sucedida!"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Cpf não encontrado!"
    )
    public ResponseEntity excluirPaciente(@PathVariable String cpf) {

        try {
            service.deletarPaciente(cpf);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity("CPF Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Excluído com Sucesso", HttpStatus.OK);

    }

    @PutMapping(value = "/editar/{cpf}")
    @Operation(summary = "Altera um paciente", description = "Método que acessa o método alterarPaciente do service passando o cpf para atualizar a lista")
    @ApiResponse(
            responseCode = "200",
            description = "Alteração bem sucedida!"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Cpf não encontrado!"
    )
    public ResponseEntity alterarPaciente(@PathVariable String cpf, @RequestBody Paciente paciente) {

        try {
            service.editarPaciente(cpf, paciente);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity("CPF Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Excluído com Sucesso", HttpStatus.OK);

    }
}
