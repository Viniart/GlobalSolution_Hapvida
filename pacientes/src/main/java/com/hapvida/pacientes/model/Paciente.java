package com.hapvida.pacientes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "paciente")
public class Paciente {
    @Id
    private String cpf;
    private String nome;
    private String rg;
    private Date dataNascimento;
    private String telefone;
    private String email;
    private String rotaImagem;

//    @OneToOne
//    @JoinColumn(name = "id_historico")
//    private Historico historico;

}
