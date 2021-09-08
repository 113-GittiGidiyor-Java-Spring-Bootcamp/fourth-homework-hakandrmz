package dev.patika.homework04.controller;

import dev.patika.homework04.dto.StudentDTO;
import dev.patika.homework04.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<StudentDTO> getAllStudents(){
        return new ResponseEntity(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable long id){
        return new ResponseEntity(studentService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("student/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable long id){
        studentService.deleteById(id);
        return new ResponseEntity<>("Student with id: " + id + " deleted",HttpStatus.OK);
    }

    @PostMapping("student/add")
    public ResponseEntity<StudentDTO> addNewStudent(@RequestBody StudentDTO studentDTO){
        studentService.save(studentDTO);
        return new ResponseEntity("Student Added",HttpStatus.OK);
    }

    @PutMapping("student/update")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
        studentService.update(studentDTO);
        return new ResponseEntity(studentDTO, HttpStatus.OK);
    }

    /**
     * method for adding student an existing course
     * @param courseId
     * @param studentDTO
     * @return Student and couse info or throws an exception
     */
    @PostMapping("student/add/{courseId}")
    public ResponseEntity<StudentDTO> addAStudentToAnExistingCourse(@PathVariable long courseId,
                                                                    @RequestBody StudentDTO studentDTO){
        studentService.addAStudentToAnExistingCourse(courseId,studentDTO);
        return new ResponseEntity(String.format("Student Name: %s added to %d",studentDTO.getName(),courseId), HttpStatus.OK);
    }

    @GetMapping("students/search/{keyword}")
    public ResponseEntity<StudentDTO> searchStudent(@PathVariable String keyword){
        return new ResponseEntity(studentService.search(keyword),HttpStatus.OK);
    }
}





















