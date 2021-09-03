package dev.patika.homework04.service;

import dev.patika.homework04.dto.CourseDTO;
import dev.patika.homework04.entity.Course;
import dev.patika.homework04.exception.CourseIsAlreadyExistException;
import dev.patika.homework04.mappers.CourseMapper;
import dev.patika.homework04.repository.CourseRepository;
import dev.patika.homework04.utils.ErrorMessageConstants;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired(required = false)
    private CourseMapper courseMapper;

    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course findById(long id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course id :" + id));
    }

    @Transactional
    public String save(CourseDTO courseDTO) {
        boolean isExist = this.isCourseExistOnDatabase(courseDTO.getCourseCode());
        if(isExist){
            throw new CourseIsAlreadyExistException(ErrorMessageConstants.COURSE_IS_ALREADY_EXIST);
        }else {
            Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
            //System.out.println(courseDTO.getCourseCode()+courseDTO.getName()+course.getCredit());
            //System.out.println(course.getCourseCode()+course.getName()+course.getCredit());
            courseRepository.save(course);
            return "Course added to database";
        }
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }

    public void update(CourseDTO courseDTO) {
        if(this.isCourseExistOnDatabase(courseDTO.getCourseCode())){
            Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
            courseRepository.save(course);
        }else {
            throw new CourseIsAlreadyExistException(ErrorMessageConstants.COURSE_IS_ALREADY_EXIST);
        }
    }

    public List<Course> search(String word) {
        return courseRepository.search(word);
    }

    private boolean isCourseExistOnDatabase(String courseCode) {
        Course course = courseRepository.findCourseByCourseCode(courseCode);

        if(course == null){
            System.out.println("false");
            return false;
        }else {
            System.out.println("true");
            return true;
        }

    }
}
