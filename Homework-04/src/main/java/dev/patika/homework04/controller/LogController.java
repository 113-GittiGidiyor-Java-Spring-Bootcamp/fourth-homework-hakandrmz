package dev.patika.homework04.controller;

import dev.patika.homework04.entity.SystemLog;
import dev.patika.homework04.service.SystemLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LogController {

    private final SystemLogService systemLogService;

    /**
     *
     * @return all logs from database
     */
    @GetMapping("/logs")
    public ResponseEntity<SystemLog> getAllLogs(){
        return new ResponseEntity(systemLogService.getAll(), HttpStatus.OK) ;
    }

    /**
     *
     * @param keyword
     * @return logs which includes keyword
     */
    @GetMapping("/logs/search/{keyword}")
    public ResponseEntity<SystemLog> searchInLogs(@PathVariable String keyword){
        return new ResponseEntity(systemLogService.search(keyword),HttpStatus.OK);
    }

}
