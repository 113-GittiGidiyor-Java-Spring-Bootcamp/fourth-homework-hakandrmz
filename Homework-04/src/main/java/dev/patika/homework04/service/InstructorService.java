package dev.patika.homework04.service;

import dev.patika.homework04.dto.InstructorDTO;
import dev.patika.homework04.entity.Instructor;
import dev.patika.homework04.exception.InstructorIsAlreadyExistException;
import dev.patika.homework04.exception.InstructorIsNotExistException;
import dev.patika.homework04.mappers.InstructorMapper;
import dev.patika.homework04.repository.InstructorRepository;
import dev.patika.homework04.utils.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired(required = false)
    private InstructorMapper instructorMapper;

    public List<Instructor> findAll() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    public Instructor findById(long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorIsNotExistException(ErrorMessageConstants.INSTRUCTOR_IS_NOT_EXIST));
    }

    public String save(InstructorDTO instructorDTO) {
        if(this.isExistOnDatabase(instructorDTO.getPhoneNumber())){
            throw new InstructorIsAlreadyExistException(ErrorMessageConstants.INSTRUCTOR_PHONE_NUMBER_MUST_BE_UNIQUE);
        }else {
            Instructor instructor = new Instructor();
            instructorMapper.updateInstructorFromDto(instructorDTO,instructor);
            instructorRepository.save(instructor);
            return "Instructor with id:" + instructorDTO.getId() + " saved";
        }
    }

    /**
     * Method that saves the instructor to database for data loader
     * @param instructor
     * @return Saved Instructor Object
     */
    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    /**
     * method for checking if phone number exist in database
     * @param phoneNumber
     * @return
     */
    private boolean isExistOnDatabase(String phoneNumber) {
        Instructor instructor = instructorRepository.findInstructorsByPhoneNumber(phoneNumber);
        if (instructor == null) {
            return false;
        }else {
            return true; }
    }

    public void deleteById(long id) {
        try {
            instructorRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new InstructorIsNotExistException(ErrorMessageConstants.INSTRUCTOR_IS_NOT_EXIST);
        }
    }

    /**
     * method for update an existing instructor from database
     * @param instructorDTO
     */
    public void update(InstructorDTO instructorDTO) {
        Instructor instructor = instructorRepository.findById(instructorDTO.getId())
                .orElseThrow(() -> new InstructorIsNotExistException(ErrorMessageConstants.INSTRUCTOR_IS_NOT_EXIST));
        instructorMapper.updateInstructorFromDto(instructorDTO,instructor);
        instructorRepository.save(instructor);
    }

}
