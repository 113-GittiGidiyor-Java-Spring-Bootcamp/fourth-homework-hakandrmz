package dev.patika.homework04.exception;

public class InstructorIsNotExistException extends RuntimeException{
    public InstructorIsNotExistException(String message){
        super(message);
    }
}
