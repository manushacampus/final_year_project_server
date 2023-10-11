package com.bit.final_project.models;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Entity
public class User {
    public enum Status{
        ACTIVE, INACTIVE
    }
    public enum Role{
        EMPLOYEE,CUSTOMER
    }
    @Id
    private String id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String password;
    private String gender;
    private String nic;
    private String dob;
    private String registered_date;
    private String image;
    private String contact;
    @Enumerated(EnumType.STRING)
    private Role user_role;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private Instant created_at;
    @UpdateTimestamp
    private Instant updated_at;

    public static User init(UserDto request){
        User user = new User();
        user.setId(Generator.getUUID());
        user.setFirst_name(request.getFirstName());
        user.setLast_name(request.getLastName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setGender(request.getGender());
        user.setNic(request.getNic());
        user.setDob(request.getDob());
        user.setRegistered_date(request.getRegisteredDate());
        user.setImage(request.getImage());
        user.setContact(request.getContact());
        user.setStatus(Status.ACTIVE);

        return user;
    }

}
