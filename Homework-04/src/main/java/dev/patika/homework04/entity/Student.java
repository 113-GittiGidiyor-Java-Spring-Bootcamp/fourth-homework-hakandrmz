package dev.patika.homework04.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends AbstractBaseEntity{

    private String name;
    private LocalDate birthDate;
    private String address;
    private String gender;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "students")
    @ToString.Exclude
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

}
