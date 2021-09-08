package dev.patika.homework04.bootstrap;

import dev.patika.homework04.dto.InstructorDTO;
import dev.patika.homework04.entity.Course;
import dev.patika.homework04.entity.Instructor;
import dev.patika.homework04.entity.InstructorType;
import dev.patika.homework04.entity.Student;
import dev.patika.homework04.service.CourseService;
import dev.patika.homework04.service.InstructorService;
import dev.patika.homework04.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * method for saving some data at start
 */
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CourseService courseService;
    @Autowired
    private InstructorService instructorService;

    @Override
    public void run(String... args) throws Exception {

        instructorService.save(Instructor.builder()
                .name("Şener")
                .address("Muğla")
                .phoneNumber("5432112341")
                .salary(1111.0)
                .type(InstructorType.VISITING).build());
        instructorService.save(Instructor.builder()
                .name("Drogba")
                .address("Chelsea")
                .phoneNumber("5492065441")
                .salary(112323.0)
                .type(InstructorType.PERMANENT).build());
        instructorService.save(Instructor.builder()
                .name("Yuri")
                .address("Moskova")
                .phoneNumber("5957423521")
                .salary(11211.0)
                .type(InstructorType.VISITING).build());

        List<Student> Course1Students = new ArrayList<>();
        Course1Students.add(Student.builder().name("Melike").address("Bursa").
                birthDate(LocalDate.of(1996,01,7)).gender("F").build());
        Course1Students.add(Student.builder().name("Özgür").address("Londra").
                birthDate(LocalDate.of(1995,02,7)).gender("M").build());
        Course1Students.add(Student.builder().name("Derya").address("Bukres").
                birthDate(LocalDate.of(1994,03,7)).gender("M").build());

        List<Student> Course2Students = new ArrayList<>();
        Course2Students.add(Student.builder().name("Hakan").address("Kutahya").
                birthDate(LocalDate.of(1999,05,7)).gender("F").build());
        Course2Students.add(Student.builder().name("Emre").address("Kayseri").
                birthDate(LocalDate.of(1998,06,7)).gender("M").build());
        Course2Students.add(Student.builder().name("Deniz").address("Konya").
                birthDate(LocalDate.of(1997,07,7)).gender("F").build());

        List<Student> Course3Students = new ArrayList<>();
        Course3Students.add(Student.builder().name("Filiz").address("Bayburt").
                birthDate(LocalDate.of(1943,05,7)).gender("F").build());
        Course3Students.add(Student.builder().name("Ali").address("Trabzon").
                birthDate(LocalDate.of(1965,06,7)).gender("M").build());
        Course3Students.add(Student.builder().name("Ezgi").address("Diyarbakır").
                birthDate(LocalDate.of(1976,07,7)).gender("F").build());

        courseService.save(Course.builder()
                .courseCode("CS102")
                .name("Computer Science101")
                .instructor(Instructor.builder()
                        .name("Şener")
                        .address("Bursa")
                        .phoneNumber("54328364541")
                        .salary(1111.0)
                        .type(InstructorType.VISITING).build())
                .students(Course1Students)
                .credit(6).build());

        courseService.save(Course.builder()
                .courseCode("CS103")
                .name("Physic")
                .instructor(Instructor.builder()
                        .name("Hülya")
                        .address("Çanakkale")
                        .phoneNumber("548674641")
                        .salary(1113.0)
                        .type(InstructorType.PERMANENT).build())
                .students(Course2Students)
                .credit(4).build());

        courseService.save(Course.builder()
                .courseCode("CS104")
                .name("Algorithm Analysis")
                .instructor(Instructor.builder()
                        .name("Fatih")
                        .address("Çanakkale")
                        .phoneNumber("576867812341")
                        .salary(1153.0)
                        .type(InstructorType.VISITING).build())
                .students(Course3Students)
                .credit(2).build());
    }
}