package org.users.api.usecases;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.users.api.domain.collection.User;
import org.users.api.domain.dto.UserDTO;
import org.users.api.repository.IUserRepository;
import org.users.api.usecases.interfaces.SaveUser;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveUserUseCase implements SaveUser {

    private final IUserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public Mono<UserDTO> save(UserDTO userDTO) {
        return userRepository.save(mapper.map(userDTO, User.class))
                .switchIfEmpty(Mono.empty())
                .map(user -> mapper.map(user, UserDTO.class));
    }
}
