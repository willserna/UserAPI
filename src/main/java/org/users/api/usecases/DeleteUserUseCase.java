package org.users.api.usecases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.users.api.repository.IUserRepository;
import org.users.api.usecases.interfaces.DeleteUser;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteUserUseCase implements DeleteUser {

    private final IUserRepository userRepository;

    @Override
    public Mono<String> delete(String id) {
        return userRepository.findById(id)
                .flatMap(user -> userRepository.delete(user).thenReturn(id))
                .switchIfEmpty(Mono.error(new RuntimeException(id)));
    }
}
