package dev.patika.homework04.service;

import dev.patika.homework04.dto.StudentDTO;
import dev.patika.homework04.entity.Course;
import dev.patika.homework04.entity.Log;
import dev.patika.homework04.entity.Student;
import dev.patika.homework04.exception.StudentAgeNotValidException;
import dev.patika.homework04.exception.StudentNotFoundException;
import dev.patika.homework04.exception.StudentNumberForOneCourseExceededException;
import dev.patika.homework04.mappers.StudentMapper;
import dev.patika.homework04.repository.CourseRepository;
import dev.patika.homework04.repository.LogRepository;
import dev.patika.homework04.repository.StudentRepository;
import dev.patika.homework04.utils.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private LogRepository logRepository;

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
    public Optional<StudentDTO> save(StudentDTO studentDTO) {
        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        if(validateStudentAge(studentDTO)){
            studentRepository.save(student);
        }else {
            logRepository.save(Log.builder().type(ErrorMessageConstants.STUDENT_AGE_NOT_VALID)
                    .info("Student age is not valid to save")
                    .build());
            throw new StudentAgeNotValidException(ErrorMessageConstants.STUDENT_AGE_NOT_VALID);
        }
        return Optional.of(studentMapper.mapFromStudentToStudentDTO(student));
    }

    public void addAStudentToAnExistingCourse(long courseId, StudentDTO studentDTO ){
        Course foundCourse = courseRepository.findById(courseId).orElseThrow(() -> new StudentNotFoundException("Student not found with id:"+studentDTO.getId()));
        int numberOfStudentOfCourse = foundCourse.getStudents().size();
        if(numberOfStudentOfCourse > 20) {
            logRepository.save(Log.builder().type(ErrorMessageConstants
                    .STUDENT_NUMBER_FOR_ONE_COURSE_EXCEED)
                    .info("A course cannot take more than 20 students")
                    .build());
            throw new StudentNumberForOneCourseExceededException(ErrorMessageConstants.STUDENT_NUMBER_FOR_ONE_COURSE_EXCEED);
        }else {
            Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
            foundCourse.getStudents().add(student);
        }
    }

    @Transactional
    public Student deleteById(long id) {
        Student foundStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        studentRepository.deleteById(id);
        return foundStudent;
    }

    @Transactional
    public void update(StudentDTO studentDTO) {
        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        studentRepository.save(student);
    }

    @Transactional
    public List<Student> search(String word) {
        return studentRepository.search(word);
    }

    private boolean validateStudentAge(StudentDTO studentDTO) {
        int studentAge = this.calculateAge(studentDTO.getBirthDate(),LocalDate.now());
        if(studentAge < 18 && studentAge > 40){
            return false;
        }else {
            return true;
        }
    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            throw new InvalidParameterException();
        }
    }
}
