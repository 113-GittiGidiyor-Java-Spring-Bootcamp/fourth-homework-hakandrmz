package dev.patika.homework04.mappers;

import dev.patika.homework04.dto.CourseDTO;
import dev.patika.homework04.entity.Course;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper {
    Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);
    CourseDTO mapFromCourseToCourseDTO(Course course);
}
