package com.bit.final_project.security.filters.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bit.final_project.commons.jwt.JWT;
import com.bit.final_project.commons.jwt.JWTContent;
import com.bit.final_project.configs.JwtConfig;
import com.bit.final_project.exceptions.http.UnauthorizeException;
import com.bit.final_project.models.User;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.security.filters.UserHelper;
import com.bit.final_project.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class JWTSecurityFilter extends BasicAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(JWTSecurityFilter.class);
    private UserService userService;
    public JWTSecurityFilter(AuthenticationManager authenticationManager, UserService userService){
        super(authenticationManager);
        this.userService=userService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // super.doFilterInternal(request, response, chain);
        String token = request.getHeader("Authorization");
        if(token == null){
            logger.debug("token not found in the request");
            chain.doFilter(request,response);
            return;
        }
        token = token.replace("Bearer ","");
        log.info("user token custom={}",token);
        try {
            JWTContent content = JWT.decode(token, JwtConfig.jwtKey);
            var userId = content.getPayload().get("userId");
            logger.info("token decoded,user ={}",userId);
            User user = this.userService.getUserById(userId);
            if(user == null){
                logger.error("user not found for id = {}",userId);
                throw new UnauthorizeException("user not found");
            }
            var role = UserHelper.getRoleOfUser(user);
            CurrentUser.setUser(user,role);
        }
        catch (JWTVerificationException exp){
            logger.error("token decode failed token = {}, error = {}",token,exp.getMessage());
            throw new UnauthorizeException("authorized token expired or invalid");
        }
        chain.doFilter(request,response);
    }
}
