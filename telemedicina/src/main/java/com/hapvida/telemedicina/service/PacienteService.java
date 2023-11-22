package com.hapvida.telemedicina.service;

import com.hapvida.telemedicina.model.Paciente;
import com.hapvida.telemedicina.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public void cadastrarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public Paciente buscarPacientePorId(String cpf) {
        Optional<Paciente> paciente = pacienteRepository.findById(cpf);

        if(paciente.isEmpty()) {
            return null;
        }

        return paciente.get();
    }

    public void deletarPaciente(String cpf) {
        if(pacienteRepository.existsById(cpf))
            pacienteRepository.deleteById(cpf);
    }

    public void editarPaciente(String cpf, Paciente pacienteNovo) {
        if(pacienteRepository.existsById(cpf))
            pacienteRepository.save(pacienteNovo);
    }
}
