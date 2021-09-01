package dev.patika.homework04.service;

import dev.patika.homework04.dto.InstructorDTO;
import dev.patika.homework04.entity.Instructor;
import dev.patika.homework04.entity.Log;
import dev.patika.homework04.exception.InstructorIsAlreadyExistException;
import dev.patika.homework04.mappers.InstructorMapper;
import dev.patika.homework04.repository.InstructorRepository;
import dev.patika.homework04.utils.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private InstructorMapper instructorMapper;

    public List<Instructor> findAll() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    public Instructor findById(long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id :" + id));
    }

    public Instructor save(InstructorDTO instructorDTO) {
        if(this.isExistOnDatabase(instructorDTO.getPhoneNumber())){
            Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
            instructorRepository.save(instructor);
            return instructor;
        }else {
            throw new InstructorIsAlreadyExistException(ErrorMessageConstants.INSTRUCTOR_PHONE_NUMBER_MUST_BE_UNIQUE);
        }
    }

    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    private boolean isExistOnDatabase(String phoneNumber) {
        Instructor instructor = instructorRepository.findInstructorsByPhoneNumber(phoneNumber);
        if (instructor == null) {
            return false;
        }else {
            return true; }
    }

    public void deleteById(long id) {
        instructorRepository.deleteById(id);
    }

    public void update(Instructor instructor) {
        Instructor foundInstructor = instructorRepository.findById(instructor.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid student id :" + instructor.getId()));
        foundInstructor.setName(instructor.getName());
        foundInstructor.setAddress(instructor.getAddress());
        foundInstructor.setPhoneNumber(instructor.getPhoneNumber());
        foundInstructor.setSalary(instructor.getSalary());
        foundInstructor.setType(instructor.getType());
        instructorRepository.save(instructor);
    }

    public List<Instructor> search(String word) {
        return instructorRepository.search(word);
    }

}
