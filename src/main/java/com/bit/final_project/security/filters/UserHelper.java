package com.bit.final_project.security.filters;

import com.bit.final_project.enums.UserRole;
import com.bit.final_project.models.User;

public class UserHelper {
    public enum Roles{
        ROLE_CUSTOMER,ROLE_EMPLOYEE
    }
    public static  String getRoleOfUser(User user){
        String role = null;
        if(user.getUser_role().equals(UserRole.EMPLOYEE)){
            role = Roles.ROLE_EMPLOYEE.toString();
        }
        else if(user.getUser_role().equals(UserRole.CUSTOMER)){
            role = Roles.ROLE_CUSTOMER.toString();
        }
        return role;

    }
}
