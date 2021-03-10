package org.rolkevin.user.bean.validation;


import org.rolkevin.user.bean.validation.group.AddUserGroup;
import org.rolkevin.user.domain.User;
import org.rolkevin.user.domain.UserDemo;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationDemo {

    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // cache the factory somewhere
        Validator validator = factory.getValidator();

        User user = new User();
        user.setPassword("*****");
        user.setName("XQ");
        user.setEmail("abcs@qq.com");
        user.setPhoneNumber("2161792");

        // 校验结果
        Set<ConstraintViolation<User>> violations = validator.validate(user,AddUserGroup.class);

        System.out.println(violations.toString());
        violations.forEach(c -> {
            System.out.println(c.getMessage());
        });
    }
}
