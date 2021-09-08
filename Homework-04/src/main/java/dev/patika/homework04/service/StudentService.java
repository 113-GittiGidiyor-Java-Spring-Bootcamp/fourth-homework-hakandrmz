package dev.patika.homework04.service;

import dev.patika.homework04.dto.StudentDTO;
import dev.patika.homework04.entity.Course;
import dev.patika.homework04.entity.Student;
import dev.patika.homework04.exception.*;
import dev.patika.homework04.mappers.StudentMapper;
import dev.patika.homework04.repository.CourseRepository;
import dev.patika.homework04.repository.LogRepository;
import dev.patika.homework04.repository.StudentRepository;
import dev.patika.homework04.utils.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired(required = false)
    private StudentMapper studentMapper;
    @Autowired
    private CourseRepository courseRepository;

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Transactional
    public Optional<Student> findById(long id) {
        Optional<Student> student = Optional.of(studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id:"+id)));
        return student;
    }

    @Transactional
    public void deleteById(long id) {
        try {
            courseRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new StudentNotFoundException(ErrorMessageConstants.STUDENT_IS_NOT_EXIST);
        }
    }

    @Transactional
    public List<Student> search(String word) {
        return studentRepository.search(word);
    }

    @Transactional
    public String save(StudentDTO studentDTO) {
        if(this.validateStudentAge(studentDTO)){
            Student student = new Student();
            studentMapper.updateStudentFromDto(studentDTO,student);
            studentRepository.save(student);
            return "Student with id: " + student.getId() + " saved";
        }else {
            throw new StudentAgeNotValidException(ErrorMessageConstants.STUDENT_AGE_NOT_VALID);
        }
    }

    /**
     * this method for adding new student to an existing course
     * if number of student in a course this method throws error
     * @param courseId
     * @param studentDTO
     */
    @Transactional
    public void addAStudentToAnExistingCourse(long courseId, StudentDTO studentDTO ){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseIsNotExistException(ErrorMessageConstants.COURSE_IS_NOT_EXIST));
        if(course.getStudents().size() >= 20) {
            throw new StudentNumberForOneCourseExceededException(ErrorMessageConstants.STUDENT_NUMBER_FOR_ONE_COURSE_EXCEED);
        }else {
            Student student = new Student();
            studentMapper.updateStudentFromDto(studentDTO,student);
            course.getStudents().add(student);
            courseRepository.save(course);
        }
    }

    /**
     * method for update an existing student in database
     * @param studentDTO
     */
    @Transactional
    public void update(StudentDTO studentDTO) {
        if(this.validateStudentAge(studentDTO)){
            Student student = studentRepository.findById(studentDTO.getId())
                    .orElseThrow(() -> new StudentNotFoundException(ErrorMessageConstants.STUDENT_IS_NOT_EXIST));
            studentMapper.updateStudentFromDto(studentDTO,student);
            studentRepository.save(student);
        }else {
            throw new StudentAgeNotValidException(ErrorMessageConstants.STUDENT_AGE_NOT_VALID);
        }
    }

    /**
     * method for checking student age is valid
     * @param studentDTO
     * @return true if student age is valid
     * else return false
     */
    private boolean validateStudentAge(StudentDTO studentDTO) {
        int studentAge = this.calculateAge(studentDTO.getBirthDate(),LocalDate.now());
        if(studentAge < 18 || studentAge > 40){
            return false;
        }else {
            return true;
        }
    }

    /**
     *method for calculating age
     * @param birthDate
     * @param currentDate
     * @return
     */
    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            throw new InvalidParameterException();
        }
    }
}
