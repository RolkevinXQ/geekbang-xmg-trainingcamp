package org.rolkevin.user.management;

import org.rolkevin.user.domain.User;

/**
 * 自定义MBean，接口命名要以MBean结尾
 */
public interface UserManagerMBean {
    // MBeanAttributeInfo 列表
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    // MBeanOperationInfo
    String toString();

    User getUser();
}
