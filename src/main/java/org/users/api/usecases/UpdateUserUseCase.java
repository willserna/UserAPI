package org.users.api.usecases;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.users.api.domain.collection.User;
import org.users.api.domain.dto.UserDTO;
import org.users.api.repository.IUserRepository;
import org.users.api.usecases.interfaces.UpdateUser;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UpdateUserUseCase implements UpdateUser {

    private final IUserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public Mono<UserDTO> update(String id, UserDTO userDTO) {
        return userRepository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(user -> {
                    userDTO.setId(user.getId());
                    return this.userRepository.save(mapper.map(userDTO, User.class));
                })
                .map(user -> mapper.map(user, UserDTO.class));
    }
}
