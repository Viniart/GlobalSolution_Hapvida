package com.hapvida.telemedicina.service;

import com.hapvida.telemedicina.model.Consulta;
import com.hapvida.telemedicina.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarTodos() {
        return consultaRepository.findAll();
    }

    public void cadastrarconsulta(Consulta consulta) {
        consultaRepository.save(consulta);
    }

    public Consulta buscarconsultaPorId(Integer id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);

        if(consulta.isEmpty()) {
            return null;
        }

        return consulta.get();
    }

    public void deletarconsulta(Integer id) {
        if(consultaRepository.existsById(id))
            consultaRepository.deleteById(id);
    }

    public void editarconsulta(Integer id, Consulta consultaNovo) {
        if(consultaRepository.existsById(id))
            consultaRepository.save(consultaNovo);
    }
}
