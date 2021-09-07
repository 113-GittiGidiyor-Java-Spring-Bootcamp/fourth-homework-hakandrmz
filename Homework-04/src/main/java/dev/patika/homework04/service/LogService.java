package dev.patika.homework04.service;

import dev.patika.homework04.entity.Log;
import dev.patika.homework04.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    /**
     * method for saving log to database
     * @param log
     */
    @Transactional
    public void save(Log log){
        logRepository.save(log);
    }

    /**
     * for getting all logs exist in database
     * @return
     */
    @Transactional
    public List<Log> getAll(){
        return (List<Log>) logRepository.findAll();
    }

    /**
     * getting all logs includes word keyword
     * @param word
     * @return
     */
    @Transactional
    public List<Log> search(String word){
        return logRepository.search(word);
    }

}
