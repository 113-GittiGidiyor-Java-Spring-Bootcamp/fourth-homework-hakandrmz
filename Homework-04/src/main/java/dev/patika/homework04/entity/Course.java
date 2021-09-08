package dev.patika.homework04.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  Course extends AbstractBaseEntity{

    private String name;
    private String courseCode;
    private int credit;

    @ManyToMany(cascade = {CascadeType.ALL})
    @Builder.Default
    @JoinTable(
            name = "Course_Student",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.ALL})
    @JsonIgnore
    private Instructor instructor;

}
