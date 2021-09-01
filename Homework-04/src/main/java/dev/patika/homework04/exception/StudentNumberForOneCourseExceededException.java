package dev.patika.homework04.exception;

public class StudentNumberForOneCourseExceededException extends RuntimeException{
    public StudentNumberForOneCourseExceededException(String msg){
        super(msg);
    }
}
