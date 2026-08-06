package com.example.employeeservice.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void testHandleException() {

        Exception ex = new RuntimeException("Demo Error");

        ResponseEntity<Map<String, Object>> response =
                handler.handleException(ex);

        assertEquals(500, response.getStatusCode().value());

        Map<String, Object> body = response.getBody();

        assertNotNull(body);
        assertEquals(500, body.get("status"));
        assertEquals("Internal Server Error", body.get("error"));
        assertEquals("Demo Error", body.get("message"));
    }

    @Test
    void testHandleValidation() throws Exception {

        Dummy dummy = new Dummy();

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(dummy, "dummy");

        bindingResult.addError(
                new FieldError(
                        "dummy",
                        "name",
                        "Name is required"
                )
        );

        Method method =
                Dummy.class.getDeclaredMethod("dummyMethod");

        MethodParameter parameter =
                new MethodParameter(method, -1);

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(
                        parameter,
                        bindingResult
                );

        ResponseEntity<Map<String, Object>> response =
                handler.handleValidation(ex);

        assertEquals(400, response.getStatusCode().value());

        Map<String, Object> body = response.getBody();

        assertNotNull(body);
        assertEquals(400, body.get("status"));
        assertEquals("Validation Failed", body.get("error"));

        Map<String, String> errors =
                (Map<String, String>) body.get("validationErrors");

        assertEquals(
                "Name is required",
                errors.get("name")
        );
    }

    static class Dummy {

        public void dummyMethod() {
        }
    }
}