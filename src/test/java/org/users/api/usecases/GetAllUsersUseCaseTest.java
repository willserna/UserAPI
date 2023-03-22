package org.users.api.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.users.api.domain.collection.User;
import org.users.api.repository.IUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetAllUsersUseCaseTest {

    @Mock
    IUserRepository repoMock;

    ModelMapper modelMapper;

    GetAllUsersUseCase service;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new GetAllUsersUseCase(repoMock, modelMapper);
    }

    @Test
    @DisplayName("getAllUsers_Success")
    void getAllUsers() {
        //Build the scenario you need
        var fluxUsers = Flux.just(new User(), new User());

        Mockito.when(repoMock.findAll()).thenReturn(fluxUsers);

        var response = service.get();

        StepVerifier.create(response)
                .expectNextCount(2)
                .verifyComplete();

        Mockito.verify(repoMock).findAll();

    }

}