package org.rolkevin.user.bean.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidAnnotationValidator.class)
public @interface UserValid {

    int idRange() default 0;

    /*int min() default 1;

    int max() default Integer.MAX_VALUE;

    String name() default "用户名不能为空";

    String password() default "";

    String email() default "";

    String phoneNumber() default "";*/

    String message() default "用户数据不合法，校验不通过";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
