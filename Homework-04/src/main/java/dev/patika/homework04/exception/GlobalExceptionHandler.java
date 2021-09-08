package dev.patika.homework04.exception;

import dev.patika.homework04.entity.Course;
import dev.patika.homework04.entity.SystemLog;
import dev.patika.homework04.service.SystemLogService;
import dev.patika.homework04.utils.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private SystemLogService systemLogService;

    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(CourseIsAlreadyExistException exc){
        SystemLog log = new SystemLog();
        log.setType("CourseIsAlreadyExistException");
        log.setDescription(ErrorMessageConstants.COURSE_IS_ALREADY_EXIST);
        systemLogService.save(log);
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(InstructorIsAlreadyExistException exc){
        SystemLog log = new SystemLog();
        log.setType("InstructorIsAlreadyExistException");
        log.setDescription(ErrorMessageConstants.INSTRUCTOR_PHONE_NUMBER_MUST_BE_UNIQUE);
        systemLogService.save(log);
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(StudentAgeNotValidException exc){
        SystemLog log = new SystemLog();
        log.setType("StudentAgeNotValidException");
        log.setDescription(ErrorMessageConstants.STUDENT_AGE_NOT_VALID);
        systemLogService.save(log);
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(StudentNumberForOneCourseExceededException exc){
        SystemLog log = new SystemLog();
        log.setType("StudentNumberForOneCourseExceededException");
        log.setDescription(ErrorMessageConstants.STUDENT_NUMBER_FOR_ONE_COURSE_EXCEED);
        systemLogService.save(log);
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsNotExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(InstructorIsNotExistException exc){
        SystemLog log = new SystemLog();
        log.setType("InstructorIsNotExistException");
        log.setDescription(ErrorMessageConstants.INSTRUCTOR_IS_NOT_EXIST);
        systemLogService.save(log);
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CourseIsNotExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(CourseIsNotExistException exc){
        SystemLog log = new SystemLog();
        log.setType("CourseIsNotExistException");
        log.setDescription(ErrorMessageConstants.COURSE_IS_NOT_EXIST);
        systemLogService.save(log);
        AppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST,exc.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppErrorResponse> handleException(StudentNotFoundException exc){
        SystemLog log = new SystemLog();
        log.setType("StudentNotFoundException");
        log.setDescription(ErrorMessageConstants.STUDENT_IS_NOT_EXIST);
        systemLogService.save(log);
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
