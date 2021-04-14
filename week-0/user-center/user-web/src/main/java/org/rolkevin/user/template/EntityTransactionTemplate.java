package org.rolkevin.user.template;

import org.hibernate.query.internal.NativeQueryImpl;
import org.rolkevin.user.domain.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

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

    public List createNativeQuery(String sql, Class<?> targetClass, List<?> params){
        Query query = null;
        if (targetClass != null){
            query = entityManager.createNativeQuery(sql,targetClass);
        }else {
            query = entityManager.createNativeQuery(sql);
        }

        for (int i = 0;i<params.size();i++){
            query.setParameter(i+1,params.get(i));
        }
        return query.getResultList();
    }

    public <R> R findObject(Class<?>targetClass,Object id){
        return (R)entityManager.find(targetClass,id);
    }
}
