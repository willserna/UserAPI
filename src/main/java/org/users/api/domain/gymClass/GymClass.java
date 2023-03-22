package org.users.api.domain.gymClass;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.users.api.domain.dto.UserDTO;

import java.util.List;
import java.util.UUID;

@Data
public class GymClass {


    private String id;

    private String className;

    private String coachName;

    private String classTime;


}
