package com.hapvida.pacientes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "tb_consulta")
public class Consulta {
    @Id
    private Integer id;
    private String especialidade;
    private String descricao;
    private String doencas;
    private String medicacao;
    private Date dataCadastro;
    private Date dataConsulta;
    private String observacao;

    private String cpf;

    @Column(length = 10, precision = 2)
    private Double valor;
}
