package com.dinelink.exceptionHandlers;

import com.dinelink.entities.CategoryErrorResponse;
import com.dinelink.entities.SubCategoryErrorResponse;
import com.dinelink.exceptions.CategoryNotFound;
import com.dinelink.exceptions.SubCategoryNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MenuExceptionHandler {
    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<CategoryErrorResponse> handleException(CategoryNotFound exception) {
        CategoryErrorResponse error = new CategoryErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage("Category not found in DB.");
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubCategoryNotFound.class)
    public ResponseEntity<SubCategoryErrorResponse> handleException(SubCategoryNotFound exception) {
        SubCategoryErrorResponse error = new SubCategoryErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage("SubCategory not found in DB.");
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
