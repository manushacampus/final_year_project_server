package com.bit.final_project.dto;

import com.bit.final_project.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private String gender;
    private String nic;
    private String dob;
    private String registeredDate;
    private String image;
    private String contact;
    private String userRole;

    public static UserDto init(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirst_name());
        userDto.setLastName(user.getLast_name());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender());
        userDto.setNic(user.getNic());
        userDto.setDob(user.getDob());
        userDto.setRegisteredDate(user.getRegistered_date());
        userDto.setImage(user.getImage());
        userDto.setContact(user.getContact());
        userDto.setUserRole(userDto.getUserRole());

        return userDto;
    }
}
