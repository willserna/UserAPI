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
import org.users.api.domain.dto.UserDTO;
import org.users.api.repository.IUserRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SaveUserUseCaseTest {

    @Mock
    IUserRepository repoMock;

    ModelMapper modelMapper;

    SaveUserUseCase service;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new SaveUserUseCase(repoMock, modelMapper);
    }

    @Test
    @DisplayName("saveUser_Success")
    void saveUser() {
        //Build the scenario you need
        var user = new User("testId", "testIdNum", "testUserName", "testEmail", true, new ArrayList<>());

        Mockito.when(repoMock.save(user)).thenReturn(Mono.just(user));

        var response = service.save(modelMapper.map(user, UserDTO.class));

        StepVerifier.create(response)
                .expectNext(modelMapper.map(user, UserDTO.class))
                .expectComplete()
                .verify();

        Mockito.verify(repoMock).save(user);

    }


}