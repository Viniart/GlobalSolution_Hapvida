package com.hapvida.telemedicina.repository;

import com.hapvida.telemedicina.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, String> {
}
