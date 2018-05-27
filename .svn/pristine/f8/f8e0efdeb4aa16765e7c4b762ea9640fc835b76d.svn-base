package com.yz.album.api;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler
    private ApiResponse unexpectedTypeExceptionHandler(UnexpectedTypeException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setFailureMsg("illegalParamsExceptionHandler",e.getMessage());
        return apiResponse;
    }

    @ExceptionHandler
    private ApiResponse missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setFailureMsg("CR01", "required parameter [" + e.getParameterName() + "] not found");
        return apiResponse;
    }

    @ExceptionHandler
    private ApiResponse ServletRequestBindingExceptionHadler(ServletRequestBindingException e){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setFailureMsg("CR02", e.getMessage());
        return apiResponse;
    }

    @ExceptionHandler
    private ApiResponse exceptionHadler(Exception e){
        e.printStackTrace();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setFailureMsg("X1", "异常：" + e.getMessage());
        return apiResponse;
    }
}
