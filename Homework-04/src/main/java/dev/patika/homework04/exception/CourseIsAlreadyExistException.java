package dev.patika.homework04.exception;

public class CourseIsAlreadyExistException extends RuntimeException{
    public CourseIsAlreadyExistException(String msg){
        super(msg);
    }
}
