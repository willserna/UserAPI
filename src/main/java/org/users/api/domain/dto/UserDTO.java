package org.users.api.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.users.api.domain.gymClass.GymClass;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;

    @NotBlank(message="ID field must be filled")
    @NotNull(message ="ID number is required")
    private String idNum;

    @NotBlank(message="Name field must be filled")
    @NotNull(message ="Name is required")
    //@Pattern(regexp="^[A-Z][a-z]*$", message="name format is required")
    private String userName;

    @NotBlank(message="Email field must be filled")
    @NotNull(message ="Email is required")
    @Pattern(regexp=".+@.+\\.[a-z]+", message="Email address is invalid")
    private String userEmail;

    private Boolean subscribed = true;

    private List<GymClass> classes;
}
