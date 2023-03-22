package org.users.api.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.users.api.domain.collection.User;
import org.users.api.repository.IUserRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseTest {

    @Mock
    IUserRepository repoMock;

    DeleteUserUseCase service;

    @BeforeEach
    void init(){

        service = new DeleteUserUseCase(repoMock);
    }

    @Test
    @DisplayName("deleteUser_Success")
    void deleteUser(){

        User user = new User("testId", "testIdNum", "testUserName", "testEmail", true, new ArrayList<>());


        Mockito.when(repoMock.findById("testId")).thenReturn(Mono.just(user));
        Mockito.when(repoMock.delete(user)).thenReturn(Mono.empty());

        var response = service.delete("testId");

        StepVerifier.create(response)
                .expectNext("testId")
                .expectComplete()
                .verify();

    }

}