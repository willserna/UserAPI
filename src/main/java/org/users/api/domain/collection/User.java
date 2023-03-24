package org.users.api.domain.collection;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.users.api.domain.gymClass.GymClass;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank(message="ID field must be filled")
    @NotNull(message ="ID number is required")
    private String idNum;

    @NotBlank(message="Name field must be filled")
    @NotNull(message ="Name is required")
    @Pattern(regexp="^[A-Z][a-z]*$", message="name format is required")
    private String userName;

    @NotBlank(message="Email field must be filled")
    @NotNull(message ="Email is required")
    @Pattern(regexp=".+@.+\\.[a-z]+", message="Email address is invalid")
    private String userEmail;

    private Boolean subscribed = true;

    private List<GymClass> classes = new ArrayList<>();
}
