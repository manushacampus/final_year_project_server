package com.bit.final_project.security.filters;

import com.bit.final_project.exceptions.CustomException;
import com.bit.final_project.exceptions.http.BaseException;
import com.bit.final_project.exceptions.http.InternalErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (BaseException exp) {
            exp.printStackTrace();
//            log.error("exception occurred error = {}", exp.getMessage());
            setErrorResponse(httpServletResponse, exp);
        } catch (Exception exp) {
            exp.printStackTrace();
//            log.error("exception occurred error = {}", exp.getMessage());
            BaseException exception = new InternalErrorException(exp.getMessage());
            this.setErrorResponse(httpServletResponse, exception);
        }
    }


    private void setErrorResponse(HttpServletResponse response, BaseException exp) {
        response.setStatus(exp.getCode().value());
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
