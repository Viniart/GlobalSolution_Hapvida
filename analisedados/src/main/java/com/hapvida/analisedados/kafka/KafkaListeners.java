package com.hapvida.analisedados.kafka;

import com.hapvida.analisedados.model.Log;
import com.hapvida.analisedados.service.LogService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private LogService service;

    public KafkaListeners(LogService service) {
        this.service = service;
    }

    @KafkaListener(topics = {"pacientes" , "consulta"}, groupId = "pacientesId")
    void listenerPaciente(String data, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        System.out.println(topic);

        if(topic.equalsIgnoreCase("pacientes")) {
            System.out.println("Algo ocorreu com o paciente: " + data);

            Log log = new Log(0, "PACIENTE", data);

            service.cadastrarLog(log);
        }

        if(topic.equalsIgnoreCase("consulta")) {
            System.out.println("Algo ocorreu com a consulta: " + data);

            Log log = new Log(0, "CONSULTA", data);

            service.cadastrarLog(log);
        }

    }

//    @KafkaListener(topics = "consulta", groupId = "consultaId")
//    void listenerConsulta(String data) {
//        System.out.println("Algo ocorreu com a consulta: " + data);
//
//        Log log = new Log(0, "CONSULTA", data);
//
//        service.cadastrarLog(log);
//    }
}
