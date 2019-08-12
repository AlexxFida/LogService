package com.fida.services;

import com.fida.models.Log;

import java.util.List;

public interface LogService {
    void save(Log log);

    List<Log> findAll();

    List<Log> findAllByDateTime(Long start, Long end);

    List<Log> findAllByDateTimeAndSource(Long start, Long end, String source);

    boolean validationLog(Log log);

    List<String> getSources();

    List<Log> getLogsBySource(String source);

    List<Log> getLogsAfterDateBySource(Long dateTime, String source);
}
