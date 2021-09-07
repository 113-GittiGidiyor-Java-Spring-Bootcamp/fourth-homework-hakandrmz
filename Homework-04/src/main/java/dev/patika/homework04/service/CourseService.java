package dev.patika.homework04.service;

import dev.patika.homework04.dto.CourseDTO;
import dev.patika.homework04.entity.Course;
import dev.patika.homework04.entity.Instructor;
import dev.patika.homework04.entity.Log;
import dev.patika.homework04.exception.CourseIsAlreadyExistException;
import dev.patika.homework04.exception.CourseIsNotExistException;
import dev.patika.homework04.exception.InstructorIsNotExistException;
import dev.patika.homework04.mappers.CourseMapper;
import dev.patika.homework04.repository.CourseRepository;
import dev.patika.homework04.repository.LogRepository;
import dev.patika.homework04.utils.ErrorMessageConstants;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired(required = false)
    private CourseMapper courseMapper;
    @Autowired
    private LogService logService;

    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course findById(long id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course id :" + id));
    }

    /**
     *gets the course dto from controller
     * use mapper to map the course from course dto
     * saving the course using repository
     * @param courseDTO
     * @return
     */
    @Transactional
    public String save(CourseDTO courseDTO) {
        if(this.isCourseExistOnDatabase(courseDTO.getCourseCode())){
            Log log = new Log(ErrorMessageConstants.COURSE_IS_NOT_EXIST);
            logService.save(log);
            throw new CourseIsAlreadyExistException(ErrorMessageConstants.COURSE_IS_ALREADY_EXIST);
        }else {
            Course course = new Course();
            courseMapper.updateCourseFromDto(courseDTO,course);
            courseRepository.save(course);
            return "Course added to database";
        }
    }

    /**
     * this method for saving courses on start
     * @param course
     * @return
     */
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(long id) {
        try {
            courseRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new CourseIsNotExistException(ErrorMessageConstants.COURSE_IS_NOT_EXIST);
        }
    }

    @Transactional
    public void update(CourseDTO courseDTO) {
        if(this.isCourseExistOnDatabase(courseDTO.getCourseCode())){
            Course course = courseRepository.findById(courseDTO.getId())
                    .orElseThrow(() -> new CourseIsNotExistException(ErrorMessageConstants.COURSE_IS_NOT_EXIST));
            courseMapper.updateCourseFromDto(courseDTO,course);
            courseRepository.save(course);
        }else {
            Log log = new Log(ErrorMessageConstants.COURSE_IS_NOT_EXIST);
            logService.save(log);
            throw new CourseIsNotExistException(ErrorMessageConstants.COURSE_IS_NOT_EXIST);
        }
    }

    /**
     * this method for controlling the course code in database
     * @param courseCode
     * @return
     */
    private boolean isCourseExistOnDatabase(String courseCode) {
        Course course = courseRepository.findCourseByCourseCode(courseCode);
        if(course == null){
            return false;
        }else {
            return true;
        }

    }
}





