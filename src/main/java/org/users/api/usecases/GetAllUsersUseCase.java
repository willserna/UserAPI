package org.users.api.usecases;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.users.api.domain.dto.UserDTO;
import org.users.api.repository.IUserRepository;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllUsersUseCase implements Supplier<Flux<UserDTO>> {

    private final IUserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public Flux<UserDTO> get(){
        return this.userRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(user -> mapper.map(user, UserDTO.class));
    }
}
