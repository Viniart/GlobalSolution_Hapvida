package com.hapvida.telemedicina.controller;

import com.hapvida.telemedicina.model.Consulta;
import com.hapvida.telemedicina.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/consultas")
public class ConsultaController {
    private ConsultaService service;

    private KafkaTemplate<String, String> kafkaTemplate;

    public ConsultaController(ConsultaService service, KafkaTemplate<String, String> kafkaTemplate) {
        this.service = service;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping
    @Operation(summary = "Retorna a lista de consultas", description = "Método que acessa o método listarTodos do service para exibir a lista de consultas")
    @ApiResponse(
            responseCode = "200",
            description = "Sucesso"
    )
    public ResponseEntity listarConsultas() {
        return new ResponseEntity(service.listarTodos(), HttpStatus.OK);
    }

    @PostMapping(value = "/novo")
    @Operation(summary = "Cadastra uma nova consulta", description = "Método que acessa o método cadastrarConsulta do service passando uma consulta a ser cadastrada")
    @ApiResponse(
            responseCode = "201",
            description = "Cadastro bem sucedido!"
    )
    public ResponseEntity cadastrarConsulta(@RequestBody Consulta consulta) {

        service.cadastrarconsulta(consulta);
        kafkaTemplate.send("consulta", consulta.getEspecialidade() + " Cadastrado!");
        return new ResponseEntity("Cadastrado com Sucesso!", HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Exclui uma consulta", description = "Método que acessa o método deletarConsulta do service passando o id para a remover da lista")
    @ApiResponse(
            responseCode = "200",
            description = "Exclusão bem sucedida!"
    )
    @ApiResponse(
            responseCode = "400",
            description = "ID não encontrado!"
    )
    public ResponseEntity excluirConsulta(@PathVariable Integer id) {

        try {
            service.deletarconsulta(id);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity("ID Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Excluído com Sucesso", HttpStatus.OK);

    }

    @PutMapping(value = "/editar/{id}")
    @Operation(summary = "Altera um paciente", description = "Método que acessa o método alteraraConsulta do service passando o id para atualizar a lista")
    @ApiResponse(
            responseCode = "200",
            description = "Alteração bem sucedida!"
    )
    @ApiResponse(
            responseCode = "400",
            description = "ID não encontrado!"
    )
    public ResponseEntity alterarConsulta(@PathVariable Integer id, @RequestBody Consulta consulta) {

        try {
            service.editarconsulta(id, consulta);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity("ID Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Excluído com Sucesso", HttpStatus.OK);

    }
}
