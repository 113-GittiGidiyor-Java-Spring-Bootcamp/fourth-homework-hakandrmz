package dev.patika.homework04.controller;

import dev.patika.homework04.entity.Log;
import dev.patika.homework04.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogController {

    LogService logService;

    /**
     *
     * @return all the logs from database
     */
    @GetMapping("/logs")
    public ResponseEntity<Log> getAllLogs(){
        return new ResponseEntity(logService.getAll(), HttpStatus.OK) ;
    }

    /**
     *
     * @param keyword
     * @return Returns logs which includes keyword
     */
    @GetMapping("/logs/search/{keyword}")
    public ResponseEntity<Log> searchInLogs(@PathVariable String keyword){
        return new ResponseEntity(logService.search(keyword),HttpStatus.OK);
    }

}
