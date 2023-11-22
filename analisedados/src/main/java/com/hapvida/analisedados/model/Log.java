package com.hapvida.analisedados.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "log")
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue
    private Integer id;
    private String evento;
    private String log;
}
