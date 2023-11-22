package com.hapvida.analisedados.repository;

import com.hapvida.analisedados.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
}
