package org.rolkevin.user.bean.validation;


import org.rolkevin.user.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserValidAnnotationValidator implements ConstraintValidator<UserValid, User> {

    private static final String REGEX_PHONE = "^1[0-9]{10}$";
    private int idRange;

    public void initialize(UserValid annotation) {
        this.idRange = annotation.idRange();
    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        boolean isValid = true;
        String message = "";
        // 获取模板信息
        //context.getDefaultConstraintMessageTemplate();

        if (value.getId() != null && value.getId() < 0){
             message +="ID需为大于0的整数\n";
             isValid = false;
        }
        if (value.getPassword() != null &&
                (value.getPassword().length() < 6 || value.getPassword().length()>32)){
            message +="密码不能低于6位,大于32位\n";
            isValid = false;
        }
        if (value.getPhoneNumber() != null && !Pattern.matches(REGEX_PHONE,value.getPhoneNumber())){
            message +="电话号码不合法\n";
            isValid = false;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return isValid;
    }
}
