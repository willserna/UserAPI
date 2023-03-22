package org.users.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.users.api.domain.gymClass.GymClass;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;

    private String idNum;

    private String userName;

    private String userEmail;

    private Boolean subscribed = true;

    private List<GymClass> classes;
}
