package dev.patika.homework04.controller;

import dev.patika.homework04.dto.InstructorDTO;
import dev.patika.homework04.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InstructorController {

    @Autowired
    private final InstructorService instructorService;

    @GetMapping("/instructors")
    public ResponseEntity<InstructorDTO> getAllInstructors(){
        return new ResponseEntity(instructorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("instructors/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable long id){
        return new ResponseEntity(instructorService.findById(id),HttpStatus.OK);
    }

    //TODO
    @PostMapping("instructor/add")
    public ResponseEntity<InstructorDTO> addNewInstructor(@RequestBody InstructorDTO instructorDTO){
        instructorService.save(instructorDTO);
        return new ResponseEntity(instructorService.save(instructorDTO),HttpStatus.OK);
    }

    //TODO
    @PutMapping("instructor/update")
    public ResponseEntity<InstructorDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO){
        instructorService.update(instructorDTO);
        return new ResponseEntity(instructorDTO, HttpStatus.OK);
    }

    @DeleteMapping("instructor/delete/{id}")
    public ResponseEntity<InstructorDTO> deleteInstructorById(@PathVariable long id){
        instructorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
