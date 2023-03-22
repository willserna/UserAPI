package org.users.api.usecases;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.users.api.domain.dto.UserDTO;
import org.users.api.repository.IUserRepository;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetUserByIdUseCase implements Function<String, Mono<UserDTO>> {

    private final IUserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public Mono<UserDTO> apply(String id){
        return userRepository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(user -> mapper.map(user, UserDTO.class));
    }
}
