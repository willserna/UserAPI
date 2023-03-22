package org.users.api.usecases.interfaces;

import org.users.api.domain.dto.UserDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UpdateUser {

    Mono<UserDTO> update(String id, UserDTO userDTO);
}
