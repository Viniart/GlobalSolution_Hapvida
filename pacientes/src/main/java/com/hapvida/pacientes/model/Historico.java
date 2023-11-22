package com.hapvida.pacientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity(name = "historico")
@AllArgsConstructor
@NoArgsConstructor
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String descricaoGeral;
    private String doencas;
    private String medicacoes;
    private Date dataCadastro;
    private String observacoes;

    @OneToOne(mappedBy = "historico")
    private Paciente paciente;

}
