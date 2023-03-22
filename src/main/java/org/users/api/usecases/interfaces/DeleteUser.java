package org.users.api.usecases.interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteUser {

    Mono<String> delete(String id);
}
