package org.users.api.domain.collection;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.users.api.domain.gymClass.GymClass;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    private String idNum;

    private String userName;

    private String userEmail;

    private Boolean subscribed = true;

    private List<GymClass> classes;
}
