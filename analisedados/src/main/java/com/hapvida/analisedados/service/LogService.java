package com.hapvida.analisedados.service;

import com.hapvida.analisedados.model.Log;
import com.hapvida.analisedados.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public List<Log> listarTodos() {
        return logRepository.findAll();
    }

    public void cadastrarLog(Log log) {
        logRepository.save(log);
    }

    public Log buscarLogPorId(Integer id) {
        Optional<Log> log = logRepository.findById(id);

        if(log.isEmpty()) {
            return null;
        }

        return log.get();
    }
}
