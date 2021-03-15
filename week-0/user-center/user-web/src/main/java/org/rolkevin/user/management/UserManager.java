package org.rolkevin.user.management;

import org.rolkevin.user.domain.User;

public class UserManager implements UserManagerMBean {

    private final User user;

    public UserManager(User user) {
        this.user = user;
    }


    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public void setId(Long id) {
        user.setId(id);
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public void setName(String name) {
        user.setName(name);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public void setPassword(String password) {
        user.setPassword(password);
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public void setEmail(String email) {
        user.setEmail(email);
    }

    @Override
    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
    }

    @Override
    public String toString() {
        return user.toString();
    }

    @Override
    public User getUser() {
        return user;
    }
}
