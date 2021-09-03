package dev.patika.homework04.mappers;

import dev.patika.homework04.dto.CourseDTO;
import dev.patika.homework04.entity.Course;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);
    CourseDTO mapFromCourseToCourseDTO(Course course);
}
