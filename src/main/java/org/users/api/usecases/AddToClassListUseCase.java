package org.users.api.usecases;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.users.api.domain.dto.UserDTO;
import org.users.api.domain.gymClass.GymClass;
import org.users.api.repository.IUserRepository;
import org.users.api.usecases.interfaces.AddClass;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class AddToClassListUseCase implements AddClass {

    private final ModelMapper mapper;
    private final IUserRepository repository;

    @Override
    public Mono<UserDTO> add(String id, GymClass gymClass) {

        return repository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(user -> {
                    if (!user.getSubscribed()){ return Mono.empty();}
                    else {var listOfClasses = user.getClasses();
                    listOfClasses.add(gymClass);
                    user.setClasses(listOfClasses);
                    return this.repository.save(user);}
                })
                .switchIfEmpty(Mono.empty())
                .map(user -> mapper.map(user, UserDTO.class));
    }

}
