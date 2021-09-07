package dev.patika.homework04.exception;

import dev.patika.homework04.entity.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(CourseIsAlreadyExistException exc){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(InstructorIsAlreadyExistException exc){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(StudentAgeNotValidException exc){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(StudentNumberForOneCourseExceededException exc){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsNotExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(InstructorIsNotExistException exc){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CourseIsNotExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(CourseIsNotExistException exc){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(StudentNotFoundException exc){
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    private AppErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        AppErrorResponse response = new AppErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

}
