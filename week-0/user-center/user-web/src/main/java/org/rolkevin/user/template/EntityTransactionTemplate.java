package org.rolkevin.user.template;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EntityTransactionTemplate {

    @Resource(name = "bean/entityManager")
    private EntityManager entityManager;

    public void doPersist(Object object) throws Throwable{
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(object);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw  e;
        }finally {
        }


    }
}
