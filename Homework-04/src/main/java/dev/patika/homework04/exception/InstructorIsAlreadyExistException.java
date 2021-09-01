package dev.patika.homework04.exception;

public class InstructorIsAlreadyExistException extends RuntimeException{
    public InstructorIsAlreadyExistException(String message){
        super(message);
    }
}
