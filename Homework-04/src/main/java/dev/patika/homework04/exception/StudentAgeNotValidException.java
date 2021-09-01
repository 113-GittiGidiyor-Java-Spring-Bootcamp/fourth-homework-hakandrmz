package dev.patika.homework04.exception;

public class StudentAgeNotValidException extends RuntimeException{
    public StudentAgeNotValidException(String msg){
        super(msg);
    }
}
