package com.hapvida.telemedicina.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hapvida.telemedicina.model.Paciente;
import com.hapvida.telemedicina.service.PacienteService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private PacienteService pacienteService;

    public KafkaListeners(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @KafkaListener(topics = "pacientes", groupId = "pacientesId")
    void listener(String data) throws JsonProcessingException {
        System.out.println("Algo ocorreu com o paciente: " + data);

        Paciente paciente = new ObjectMapper().readValue(data, Paciente.class);
        System.out.println(paciente.getCpf());
        pacienteService.cadastrarPaciente(paciente);
    }
}
