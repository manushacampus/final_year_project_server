package com.bit.final_project.security.filters;

import com.bit.final_project.exceptions.CustomException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain){

        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (CustomException exp) {
            exp.printStackTrace();
//            logger.error("exception occurred error = {}", exp.getMessage());
            setErrorResponse(httpServletResponse, exp);
        } catch (Exception exp) {
            exp.printStackTrace();
//            logger.error("exception occurred error = {}", exp.getMessage());
            CustomException exception = new CustomException(CustomException.INTERNAL_SERVER_ERROR, exp.getMessage());
            this.setErrorResponse(httpServletResponse, exception);
        }
    }

    private void setErrorResponse(HttpServletResponse response, CustomException exp) {
        response.setStatus(exp.getStatus().value());
        response.setContentType("application/json");
        try {
            String json = exp.getJsonAsString();
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return !path.startsWith("/api/");
    }
}
