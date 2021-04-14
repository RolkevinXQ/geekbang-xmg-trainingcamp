package org.rolkevin.user.service.impl;

import org.rolkevin.user.bean.validation.group.AddUserGroup;
import org.rolkevin.user.domain.User;
import org.rolkevin.user.repository.DatabaseUserRepository;
import org.rolkevin.user.repository.JpaUserRepository;
import org.rolkevin.user.response.ResponseResult;
import org.rolkevin.user.service.UserService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Resource(name = "bean/entityManager")
    private EntityManager entityManager;

    @Resource(name = "bean/Validator")
    private Validator validator;

    @Resource(name = "bean/jpaUserRepository")
    private JpaUserRepository jpaUserRepository;

//    private final DatabaseUserRepository databaseUserRepository;
//
//    public UserServiceImpl(DatabaseUserRepository databaseUserRepository) {
//        this.databaseUserRepository = databaseUserRepository;
//    }

    @Override
    public boolean register(User user) {
        //return databaseUserRepository.save(user);
        //Set<ConstraintViolation<User>> violations = validator.validate(user, AddUserGroup.class);

            Set<ConstraintViolation<User>> violations = validator.validate(user, AddUserGroup.class);
            violations.forEach(v ->{
                System.out.println(v.getMessage());
            });
            return jpaUserRepository.save(user);

    }

    @Override
    public ResponseResult registerR(User user ){
        Set<ConstraintViolation<User>> violations = validator.validate(user, AddUserGroup.class);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(1);
        String message = "";
        if (violations.size()>0){
            responseResult.setTitle("基础数据校验不通过");
            responseResult.setCode(-1);
            String m = violations.stream().map(v ->v.getMessage()).collect(Collectors.joining());
            violations.forEach(v ->{
                System.out.println(v.getMessage());
            });
            responseResult.setContent(m);
        }
        if (responseResult.getCode() < 0){
            return responseResult;
        }
        jpaUserRepository.save(user);

        return responseResult;

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
        return jpaUserRepository.getById(id);
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;//databaseUserRepository.getByNameAndPassword(name,password);
    }

    @Override
    public Collection<User> queryAll() {
        return null;
        // databaseUserRepository.getAll();
    }
}
