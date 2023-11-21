package com.hapvida.pacientes.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "pacientes", groupId = "pacientesId")
    void listener(String data) {
        System.out.println("Algo ocorreu com o paciente: " + data);
    }
}
