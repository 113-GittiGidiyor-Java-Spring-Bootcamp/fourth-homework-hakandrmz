package dev.patika.homework04.mappers;

import dev.patika.homework04.dto.InstructorDTO;
import dev.patika.homework04.dto.StudentDTO;
import dev.patika.homework04.entity.Instructor;
import dev.patika.homework04.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    void updateStudentFromDto(StudentDTO studentDTO, @MappingTarget Student Student);
    void updateStudentDtoFromStudent(Student Student, @MappingTarget StudentDTO studentDTO);
}
