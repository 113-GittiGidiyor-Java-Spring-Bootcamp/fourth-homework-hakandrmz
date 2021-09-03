package dev.patika.homework04.controller;

import dev.patika.homework04.entity.Log;
import dev.patika.homework04.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LogController {

    LogService logService;

    @GetMapping("/logs")
    public ResponseEntity<Log> getAllLogs(){
        return new ResponseEntity<Log>((Log) logService.getAll(), HttpStatus.OK) ;
    }

}
