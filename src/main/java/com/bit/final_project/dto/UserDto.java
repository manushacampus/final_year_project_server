package com.bit.final_project.dto;

import com.bit.final_project.commons.URL;
import com.bit.final_project.enums.UserRole;
import com.bit.final_project.models.User;
import lombok.Getter;
import lombok.Setter;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
public class UserDto {
    private String id;
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
    private String designation;

    public static UserDto init(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirst_name());
        userDto.setLastName(user.getLast_name());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender());
        userDto.setNic(user.getNic());
        if (user.getBirthday() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            userDto.setDob(user.getBirthday().format(formatter));
        }
        if (user.getRegistered_date() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            userDto.setRegisteredDate(user.getRegistered_date().format(formatter));
        }

        if (user.getUser_role().equals(UserRole.EMPLOYEE)){
            if (user != null && user.getImage() != null && !user.getImage().isEmpty()) {
                userDto.setImage(URL.fileStorageUrl.replace("{type}", "employee").replace("{fileName}", user.getImage()));
            }
        }
        if (user.getUser_role().equals(UserRole.CUSTOMER)){
            if (user != null && user.getImage() != null && !user.getImage().isEmpty()) {
                userDto.setImage(URL.fileStorageUrl.replace("{type}", "customer").replace("{fileName}", user.getImage()));
            }
        }

        userDto.setContact(user.getContact());
        userDto.setUserRole(String.valueOf(user.getUser_role()));

        return userDto;
    }
}
