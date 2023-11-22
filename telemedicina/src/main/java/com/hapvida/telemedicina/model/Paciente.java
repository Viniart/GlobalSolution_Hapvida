package com.hapvida.telemedicina.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "tb_paciente")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paciente {
    @Id
    private String cpf;
    private String nome;
    private String rg;
    private Date dataNascimento;
    private String telefone;
    private String email;
    private String rotaImagem;
}
