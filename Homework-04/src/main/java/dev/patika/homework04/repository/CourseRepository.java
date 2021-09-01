package dev.patika.homework04.repository;

import dev.patika.homework04.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {
    @Query("SELECT c FROM Course c WHERE c.name LIKE %?1%"
            +" OR c.courseCode LIKE %?1%"
            +" OR c.instructor.name LIKE %?1%")
    List<Course> search(String word);

    Course findCourseByCourseCode(String word);
}
