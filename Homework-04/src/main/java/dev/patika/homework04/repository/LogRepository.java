package dev.patika.homework04.repository;

import dev.patika.homework04.entity.SystemLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<SystemLog,Long> {
    @Query("SELECT l FROM SystemLog l WHERE l.type LIKE %?1%" +
                    "OR l.description LIKE %?1%")
    List<SystemLog> search(String word);
}
