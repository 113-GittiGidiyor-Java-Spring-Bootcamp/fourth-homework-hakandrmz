package dev.patika.homework04.mappers;

import dev.patika.homework04.dto.CourseDTO;
import dev.patika.homework04.entity.Course;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    void updateCourseFromDto(CourseDTO courseDTO, @MappingTarget Course course);
    void updateCourseDtoFromCourse(Course course,@MappingTarget CourseDTO courseDTO);
}
