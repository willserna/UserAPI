package org.users.api.usecases;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.users.api.domain.dto.UserDTO;
import org.users.api.domain.gymClass.GymClass;
import org.users.api.repository.IUserRepository;
import org.users.api.usecases.interfaces.RemoveClass;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class RemoveFromClassListUseCase implements RemoveClass {

    private final ModelMapper mapper;
    private final IUserRepository repository;

    @Override
    public Mono<UserDTO> remove(String id, GymClass gymClass) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(user -> {
                    var listOfClasses = user.getClasses();
                    listOfClasses.remove(gymClass);
                    user.setClasses(listOfClasses);
                    return this.repository.save(user);
                })
                .switchIfEmpty(Mono.empty())
                .map(user -> mapper.map(user, UserDTO.class));
    }



}
