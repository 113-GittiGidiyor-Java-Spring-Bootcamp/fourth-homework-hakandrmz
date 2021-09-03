package dev.patika.homework04.repository;

import dev.patika.homework04.entity.Instructor;
import dev.patika.homework04.entity.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<Log,Long> {

    @Query("SELECT l FROM Log l WHERE l.info LIKE %?1%"
            +" OR l.type LIKE %?1%")
    List<Log> search(String word);



}
