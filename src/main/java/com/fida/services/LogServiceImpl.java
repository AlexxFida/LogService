package com.fida.services;

import com.fida.models.Log;
import com.fida.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void save(Log log) {
        logRepository.save(log);
    }

    @Override
    public List<Log> findAll() {
        return (List<Log>) logRepository.findAll();
    }

    @Override
    public List<Log> findAllByDateTime(Long start, Long end) {

        Pageable pageable = new PageRequest(0, 50);
        return logRepository.findAllByDateTimeAfterAndDateTimeBefore(start, end, pageable);
    }

    @Override
    public List<Log> findAllByDateTimeAndSource(Long start, Long end, String source) {
        Pageable pageable = new PageRequest(0, 50);
        return logRepository.findAllByDateTimeAfterAndDateTimeBeforeAndSource(start, end, source, pageable);
    }

    @Override
    public List<String> getSources(){
        return logRepository.getSources();
    }

    @Override
    public List<Log> getLogsBySource(String source) {
        return logRepository.findAllBySource(source);
    }

    @Override
    public List<Log> getLogsAfterDateBySource(Long dateTime, String source) {
        return logRepository.findAllByDateTimeAfterAndSource(dateTime, source);
    }

    @Override
    public boolean validationLog(Log log) {
        return !log.getMessage().equals("") && !log.getSource().equals("");
    }
}
