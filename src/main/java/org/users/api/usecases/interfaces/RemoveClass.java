package org.users.api.usecases.interfaces;

import org.users.api.domain.dto.UserDTO;
import org.users.api.domain.gymClass.GymClass;
import reactor.core.publisher.Mono;

public interface RemoveClass {
    Mono<UserDTO> remove(String id, GymClass gymClass);
}
