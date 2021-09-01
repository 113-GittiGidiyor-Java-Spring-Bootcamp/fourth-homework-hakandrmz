package dev.patika.homework04.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instructor extends AbstractBaseEntity{

    @Enumerated(EnumType.STRING)
    @NonNull
    private InstructorType type;

    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String phoneNumber;
    @NonNull
    private double salary;

    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<Course> courses;

}
