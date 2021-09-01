package dev.patika.homework04.service;

import dev.patika.homework04.dto.StudentDTO;
import dev.patika.homework04.entity.Course;
import dev.patika.homework04.entity.Student;
import dev.patika.homework04.exception.StudentAgeNotValidException;
import dev.patika.homework04.exception.StudentNumberForOneCourseExceededException;
import dev.patika.homework04.mappers.StudentMapper;
import dev.patika.homework04.repository.CourseRepository;
import dev.patika.homework04.repository.StudentRepository;
import dev.patika.homework04.utils.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
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
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseRepository courseRepository;

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }
    public Student findById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with id: "+id+" could not found"));
        return student;
    }

    @Transactional
    public StudentDTO save(StudentDTO studentDTO) {
        if(validateStudentAge(studentDTO)){
            Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
            studentRepository.save(student);
        }else {
            throw new StudentAgeNotValidException(ErrorMessageConstants.STUDENT_AGE_NOT_VALID);
        }
        return null;
    }

    public void addAStudentToAnExistingCourse(long courseId, StudentDTO studentDTO ){
        Course foundCourse = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException());
        int numberOfStudentOfCourse = foundCourse.getStudents().size();
        if(numberOfStudentOfCourse > 20) {
            throw new StudentNumberForOneCourseExceededException(ErrorMessageConstants.STUDENT_NUMBER_FOR_ONE_COURSE_EXCEED);
        }else {
            Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
            foundCourse.getStudents().add(student);
        }

    }

    @Transactional
    public void deleteById(long id) {
        Student foundStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        studentRepository.deleteById(id);
    }

    @Transactional
    public void update(StudentDTO studentDTO) {
        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        studentRepository.save(student);
    }

    @Transactional
    public List<?> getGendersWithGrouping() {
        return studentRepository.getGendersWithGrouping();
    }

    @Transactional
    public void deleteByName(String name) {
        studentRepository.deleteByName(name);
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
