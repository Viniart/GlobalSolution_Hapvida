package com.hapvida.pacientes.repository;

import com.hapvida.pacientes.model.Historico;
import com.hapvida.pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
}
