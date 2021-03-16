package org.rolkevin.user.management;

import org.rolkevin.user.domain.User;

import java.util.LinkedList;
import java.util.List;

public class UserManager implements UserManagerMXBean {

    private  User user;

    private List<User> userList = new LinkedList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }



    public UserManager(User user) {
        this.user = user;
        userList.add(user);
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


    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }


    public int  getUserCount(){
        return this.userList.size();
    }

    public void removeUserFromList(String name){
        userList.forEach(user1 -> {
            if (user1.getName().equals(name)){
                userList.remove(user1);
            }
        });
    }
}
