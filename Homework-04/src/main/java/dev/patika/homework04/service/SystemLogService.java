package dev.patika.homework04.service;

import dev.patika.homework04.entity.SystemLog;
import dev.patika.homework04.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemLogService {

    @Autowired
    private LogRepository logRepository;

    /**
     * method for saving log to database
     * @param log
     */
    @Transactional
    public void save(SystemLog log){
        logRepository.save(log);
    }

    /**
     * for getting all logs exist in database
     * @return
     */
    public List<SystemLog> getAll(){
        return (List<SystemLog>) logRepository.findAll();
    }

    /**
     * getting all logs includes word keyword
     * @param word
     * @return
     */
    @Transactional
    public List<SystemLog> search(String word){
        return logRepository.search(word);
    }

}
