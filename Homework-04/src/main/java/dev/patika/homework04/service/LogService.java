package dev.patika.homework04.service;

import dev.patika.homework04.entity.Log;
import dev.patika.homework04.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void save(Log log){
        logRepository.save(log);
    }

    public List<Log> getAll(){
        return (List<Log>) logRepository.findAll();
    }

}
