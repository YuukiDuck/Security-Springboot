package duck.spring.tutorial.service.user;

import duck.spring.tutorial.model.User;

import java.util.UUID;

public interface UserService {
    User createUser(User user);

    void deleteUserById(UUID id);
}


