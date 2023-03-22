package org.users.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.users.api.domain.dto.UserDTO;
import org.users.api.usecases.*;

import javax.ws.rs.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllUsers(GetAllUsersUseCase useCase) {
        return route(GET("/users"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), UserDTO.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> getUserById(GetUserByIdUseCase useCase){
        return route(GET("/users/{id}"),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(userDTO -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(userDTO))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> saveUser(SaveUserUseCase useCase) {
        return route(POST("/users").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class)
                        .flatMap(userDTO -> useCase.save(userDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build())));
    }

    @Bean
    public  RouterFunction<ServerResponse> updateUser(UpdateUserUseCase useCase){
        return route(PUT("/users/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class)
                        .flatMap(userDTO -> useCase.update(request.pathVariable("id"), userDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build())));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteUser(DeleteUserUseCase useCase) {
        return route(DELETE("/users/{id}"),
                request -> useCase.delete(request.pathVariable("id"))
                        .flatMap(s -> ServerResponse.ok()
                                .bodyValue("User with id "+s+" has been deleted"))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));

    }
}
