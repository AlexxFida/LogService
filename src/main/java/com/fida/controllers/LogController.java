package com.fida.controllers;

import com.fida.models.Log;
import com.fida.services.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @PostMapping("/log")
    public ResponseEntity<Object> addLog(@RequestBody Log log) {
        if (logService.validationLog(log)) {
                logService.save(log);
                logger.info(log + " saved");
                return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @Deprecated
    @CrossOrigin
    @GetMapping("/logs")
    public Collection<Log> getAllLogs(){
        return logService.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "/logs", params = {"start", "end"})
    public Collection<Log> getLogs(Long start, Long end){
        return logService.findAllByDateTime(start, end);
    }

    @CrossOrigin
    @GetMapping(value = "/logs", params = {"start", "end", "source"})
    public Collection<Log> getLogs(Long start, Long end, String source){
        return logService.findAllByDateTimeAndSource(start, end, source);
    }

    @CrossOrigin
    @GetMapping(value = "/logs", params = {"source"})
    public Collection<Log> getLogs(String source){
        return logService.getLogsBySource(source);
    }

    @CrossOrigin
    @GetMapping(value = "/sources")
    public Collection<String> getLogs(){
        return logService.getSources();
    }

    @CrossOrigin
    @GetMapping(value = "/logs", params = {"dateTime", "source"})
    public Collection<Log> getLogsAfterDateBySource(Long dateTime, String source){
        return logService.getLogsAfterDateBySource(dateTime, source);
    }
}
