package com.bit.final_project.security.filters;

import com.bit.final_project.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

public class CurrentUser {
    public static User getUser(){
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof User){
            return (User) principle;
        }
        return null;
    }
    public static void setUser(User user, String role){
        var authorityList = new ArrayList<GrantedAuthority>();
        authorityList.add(new SimpleGrantedAuthority(role));
        Authentication auth = new UsernamePasswordAuthenticationToken(user,null,authorityList);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    public static void setUser(User user){
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Authentication auth = new UsernamePasswordAuthenticationToken(user,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
