package dev.patika.homework04.mappers;

import dev.patika.homework04.dto.InstructorDTO;
import dev.patika.homework04.entity.Instructor;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {
    Instructor mapFromInstructorDTOtoInstructor(InstructorDTO instructorDTO);
    InstructorDTO mapFromInstructorToInstructorDTO(Instructor instructor);
}
