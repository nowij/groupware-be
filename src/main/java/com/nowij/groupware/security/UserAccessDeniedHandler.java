package com.nowij.groupware.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowij.groupware.exception.ErrorObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Set response code
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // Set response content type to JSON
        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
        errorObject.setMessage("Access Forbidden");

        // Add content to the response
        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(errorObject);
        response.getWriter().write(json);
    }
}
