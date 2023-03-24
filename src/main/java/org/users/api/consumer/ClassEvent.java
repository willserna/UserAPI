package org.users.api.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.users.api.domain.gymClass.GymClass;

@Data
@AllArgsConstructor
public class ClassEvent {

    private String idUser;
    private GymClass classSubscribed;
    private String eventType;
}
