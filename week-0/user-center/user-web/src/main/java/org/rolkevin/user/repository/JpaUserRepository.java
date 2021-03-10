package org.rolkevin.user.repository;

import org.rolkevin.user.domain.User;
import org.rolkevin.user.template.EntityTransactionTemplate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JpaUserRepository implements UserRepository {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Resource(name = "bean/entityTransactionTemplate")
    private EntityTransactionTemplate entityTransactionTemplate;

    @Override
    public boolean save(User user) {
        boolean result = true;
        try {
            entityTransactionTemplate.doPersist(user);
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE,"保存失败",throwable);
            result = false;
        }
        return result;
    }

    @Override
    public boolean deleteById(Long userId) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User getById(Long userId) {
        return null;
    }

    @Override
    public User getByNameAndPassword(String userName, String password) {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }
}
