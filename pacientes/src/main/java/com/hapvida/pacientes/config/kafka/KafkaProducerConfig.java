package com.hapvida.pacientes.config.kafka;

import com.hapvida.pacientes.model.Paciente;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(SslConfigs.DEFAULT_SSL_ENDPOINT_IDENTIFICATION_ALGORITHM, "https");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + "HPDGBBLKHYUAAJ2H" + "\" password=\"" + "YRxAu20D2HloRrVvIxjnb0h22loGeVZWG0K0E8Z5iLoclaXNcbl2FWUj9rKUs+ng" + "\";");
        props.put("security.protocol", "SASL_SSL");

        return props;
    }

    //    Change <String, String> to <String, Modelo>
    @Bean
    public ProducerFactory<String, Paciente> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, Paciente> kafkaTemplate(ProducerFactory<String, Paciente> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
