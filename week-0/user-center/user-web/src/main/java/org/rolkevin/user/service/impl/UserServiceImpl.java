package org.rolkevin.user.service.impl;

import org.rolkevin.user.domain.User;
import org.rolkevin.user.repository.DatabaseUserRepository;
import org.rolkevin.user.service.UserService;

public class UserServiceImpl implements UserService {

    private final DatabaseUserRepository databaseUserRepository;

    public UserServiceImpl(DatabaseUserRepository databaseUserRepository) {
        this.databaseUserRepository = databaseUserRepository;
    }

    @Override
    public boolean register(User user) {
        return databaseUserRepository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return databaseUserRepository.getByNameAndPassword(name,password);
    }
}
