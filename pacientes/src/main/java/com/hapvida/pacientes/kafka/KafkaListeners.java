package com.hapvida.pacientes.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hapvida.pacientes.model.Consulta;
import com.hapvida.pacientes.model.Historico;
import com.hapvida.pacientes.model.Paciente;
import com.hapvida.pacientes.service.HistoricoService;
import com.hapvida.pacientes.service.PacienteService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class KafkaListeners {

    private HistoricoService historicoService;

    private PacienteService pacienteService;

    public KafkaListeners(HistoricoService historicoService, PacienteService pacienteService) {
        this.historicoService = historicoService;
        this.pacienteService = pacienteService;
    }

    @KafkaListener(topics = "consulta", groupId = "consultaId")
    void listener(String data) throws JsonProcessingException {
        System.out.println("Algo ocorreu com a consulta: " + data);

        Consulta consulta = new ObjectMapper().readValue(data, Consulta.class);
        System.out.println(consulta.getDescricao());

        Paciente paciente = pacienteService.buscarPacientePorId(consulta.getCpf());

        if(paciente != null ) {
            if(paciente.getHistorico() == null) {
                Historico historico = new Historico(0, consulta.getDescricao(), consulta.getDoencas(),
                        consulta.getMedicacao(),
                        new Date(), consulta.getObservacao(), paciente);

                paciente.setHistorico(historico);
                pacienteService.editarPaciente(paciente.getCpf(), paciente);
//                historicoService.cadastrarHistorico(historico);
            }
            else {
                Historico historico = paciente.getHistorico();

                historico.setDoencas(historico.getDoencas() + " || " + consulta.getDoencas());
                historico.setDescricaoGeral(historico.getDescricaoGeral()+ " || " + consulta.getObservacao());
                historico.setMedicacoes(historico.getMedicacoes() + " || " + consulta.getMedicacao());

                historicoService.editarHistorico(historico.getId(), historico);
            }
        }

    }
}
