package dev.patika.homework04.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String msg){
        super(msg);
    }
}
