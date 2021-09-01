package dev.patika.homework04.repository;

import dev.patika.homework04.entity.Course;
import dev.patika.homework04.entity.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor,Long> {
    @Query("SELECT i FROM Instructor i WHERE i.name LIKE %?1%"
            +" OR i.address LIKE %?1%"
            +" OR i.phoneNumber LIKE %?1%")
    List<Instructor> search(String word);

    Instructor findInstructorsByPhoneNumber(String word);
}
