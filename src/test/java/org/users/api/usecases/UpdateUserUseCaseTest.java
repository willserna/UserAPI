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
class UpdateUserUseCaseTest {

    @Mock
    IUserRepository repoMock;

    ModelMapper modelMapper;

    UpdateUserUseCase service;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new UpdateUserUseCase(repoMock, modelMapper);
    }

    @Test
    @DisplayName("updateUser_Success")
    void updateUser(){

        var user = new User("testId", "testIdNum", "testUserName", "testEmail", true, new ArrayList<>());
        var userU = new User("testId", "testIdNum", "testUserNameUpdate", "testEmailUpdate", true, new ArrayList<>());



        Mockito.when(repoMock.findById("testId")).thenReturn(Mono.just(user));
        Mockito.when(repoMock.save(Mockito.any())).thenReturn(Mono.just(userU));

        var response = service.update("testId", modelMapper.map(userU, UserDTO.class));

        StepVerifier.create(response)
                .expectNext(modelMapper.map(userU, UserDTO.class))
                .expectComplete()
                .verify();

        Mockito.verify(repoMock).save(userU);
        Mockito.verify(repoMock).findById("testId");
    }

}