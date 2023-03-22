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
class GetUserByIdUseCaseTest {

    @Mock
    IUserRepository repoMock;

    ModelMapper modelMapper;

    GetUserByIdUseCase service;

    @BeforeEach
    void init(){
        modelMapper = new ModelMapper();
        service = new GetUserByIdUseCase(repoMock, modelMapper);
    }

    @Test
    @DisplayName("getUserByID_Success")
    void getUserByID(){

        var user = new User("testId", "testIdNum", "testUserName", "testEmail", true, new ArrayList<>());


        Mockito.when(repoMock.findById(Mockito.any(String.class))).thenReturn(Mono.just(user));

        var response = service.apply("testId");

        StepVerifier.create(response)
                .expectNext(modelMapper.map(user, UserDTO.class))
                .expectComplete()
                .verify();

        Mockito.verify(repoMock).findById("testId");
    }

}