package org.users.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.users.api.domain.collection.User;

@Repository
public interface IUserRepository extends ReactiveMongoRepository<User, String> {
}
