package com.bit.final_project.security.filters;

import com.bit.final_project.models.User;

public class UserHelper {
    public enum Roles{
        ROLE_HR,ROLE_EMPLOYEE
    }
    public static  String getRoleOfUser(User user){
//
        String role = null;
//        if(user.getType().equals(User.Type.HR)){
//            role = Roles.ROLE_HR.toString();
//        }
//        else if(user.getType().equals(User.Type.EMPLOYEE)){
//            role = Roles.ROLE_EMPLOYEE.toString();
//        }
        return role;

    }
}
