package dev.patika.homework04.mappers;

import dev.patika.homework04.dto.StudentDTO;
import dev.patika.homework04.entity.Student;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Mapper
public interface StudentMapper {
    Student mapFromStudentDTOtoStudent(StudentDTO dto);
    StudentDTO mapFromStudentToStudentDTO(Student course);
}
