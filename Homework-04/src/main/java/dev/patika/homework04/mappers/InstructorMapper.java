package dev.patika.homework04.mappers;

import dev.patika.homework04.dto.InstructorDTO;
import dev.patika.homework04.entity.Instructor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    void updateInstructorFromDto(InstructorDTO instructorDTO, @MappingTarget Instructor instructor);
    void updateInstroctorDTOFromInstructor(Instructor instructor, @MappingTarget InstructorDTO instructorDTO);
}
