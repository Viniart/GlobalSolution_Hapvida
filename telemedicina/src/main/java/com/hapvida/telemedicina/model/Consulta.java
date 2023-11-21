package com.hapvida.telemedicina.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "consulta")
public class Consulta {
    @Id
    private Integer id;
    private String especialidade;
    private String descricao;
    private Date dataCadastro;
    private Date dataConsulta;
    private String observacao;
    @Column(length = 10, precision = 2)
    private Double valor;
}
