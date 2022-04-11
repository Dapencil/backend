package com.project.backend.models.ResponseModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.backend.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@Setter
@Getter
public class UserResponse {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate regisDate;
    private LocalDate DOB;
    private Integer totalMile;
    private String role;

    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.regisDate = user.getRegisDate();
        this.DOB = user.getDOB();
        this.totalMile = user.getTotalMile();
        this.role = user.getRole();
    }
}
