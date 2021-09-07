package dev.patika.homework04.exception;

public class CourseIsNotExistException extends RuntimeException{
    public CourseIsNotExistException(String message){
        super(message);
    }
}
