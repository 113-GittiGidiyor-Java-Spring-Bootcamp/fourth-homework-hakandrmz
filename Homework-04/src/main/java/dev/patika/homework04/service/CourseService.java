package dev.patika.homework04.service;

import dev.patika.homework04.dto.CourseDTO;
import dev.patika.homework04.entity.Course;
import dev.patika.homework04.exception.CourseIsAlreadyExistException;
import dev.patika.homework04.mappers.CourseMapper;
import dev.patika.homework04.repository.CourseRepository;
import dev.patika.homework04.utils.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;

    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course findById(long id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course id :" + id));
    }

    public Course save(CourseDTO courseDTO) {
        if(this.isExistOnDatabase(courseDTO.getCourseCode())){
            Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
            courseRepository.save(course);
            return course;

        }else {
            throw new CourseIsAlreadyExistException(ErrorMessageConstants.COURSE_IS_ALREADY_EXIST);
        }
    }

    private boolean isExistOnDatabase(String courseCode) {
        Course course = courseRepository.findCourseByCourseCode(courseCode);
        if (course == null){
            return false;
        }else {
            return true; }
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }

    public void update(Course course) {
        Course willUpdateCourse = courseRepository.findById(course.getId()).orElseThrow(() -> new IllegalArgumentException());
        willUpdateCourse.setName(course.getName());
        willUpdateCourse.setCourseCode(course.getCourseCode());
        willUpdateCourse.setCredit(course.getCredit());
        courseRepository.save(course);
    }

    public List<Course> search(String word) {
        return courseRepository.search(word);
    }
}
