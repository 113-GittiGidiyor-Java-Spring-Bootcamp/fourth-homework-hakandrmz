package dev.patika.homework04.controller;

import com.sun.xml.bind.v2.TODO;
import dev.patika.homework04.dto.CourseDTO;
import dev.patika.homework04.entity.Course;
import dev.patika.homework04.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<CourseDTO> getAllCourse(){
        return new ResponseEntity(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("courses/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable long id){
        return new ResponseEntity(courseService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("course/delete/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable long id){
        courseService.deleteById(id);
        return new ResponseEntity<>("Instructed with id " + id + " deleted",HttpStatus.OK);
    }

    @PostMapping("course/add")
    public ResponseEntity<CourseDTO> addNewCourse(@RequestBody CourseDTO courseDTO){
        courseService.save(courseDTO);
        return new ResponseEntity("Course added to database",HttpStatus.OK);
    }

    @PutMapping("course/update")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO){
        courseService.update(courseDTO);
        return new ResponseEntity(courseDTO, HttpStatus.OK);
    }
}
