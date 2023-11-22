package com.hapvida.pacientes.service;

import com.hapvida.pacientes.model.Historico;
import com.hapvida.pacientes.model.Paciente;
import com.hapvida.pacientes.repository.HistoricoRepository;
import com.hapvida.pacientes.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    public List<Historico> listarTodos() {
        return historicoRepository.findAll();
    }

    public void cadastrarHistorico(Historico historico) {
        historicoRepository.save(historico);
    }

    public Historico buscarHistoricoPorId(Integer id) {
        Optional<Historico> historico = historicoRepository.findById(id);

        if(historico.isEmpty()) {
            return null;
        }

        return historico.get();
    }

    public void deletarHistorico(Integer id) {
        if(historicoRepository.existsById(id))
            historicoRepository.deleteById(id);
    }

    public void editarHistorico(Integer id, Historico historicoNovo) {
        if(historicoRepository.existsById(id))
            historicoRepository.save(historicoNovo);
    }
}
