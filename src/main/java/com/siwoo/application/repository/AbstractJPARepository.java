package com.siwoo.application.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class AbstractJPARepository<T extends  Object> implements Repository<T> {

    @PersistenceContext protected EntityManager entityManager;
    private Class<T> domainClass;

    private Class<T> getDomainClass(){
        if(domainClass == null){
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    private String getDomainClassName(){
        return getDomainClass().getName();
    }
    @Override
    public void create(T t) {
        Method method = ReflectionUtils
                /*
                * @param clazz the class to introspect
	            * @param name the name of the method
	            * @param paramTypes the parameter types of the method
                */
                .findMethod(getDomainClass(),"setDateCreated",new Class[]{LocalDateTime.class});
        if(method != null){
            try{
                /*
                * @param obj  the object the underlying method is invoked from
                * @param args the arguments used for the method call
                * @return the result of dispatching the method represented by
                * this object on {@code obj} with parameters
                */
                method.invoke(t, LocalDateTime.now());
            }catch (Exception e){
                /* Ignore */
            }
        }
        entityManager.persist(t);
    }

    @Override
    public T get(Serializable id) {
        return entityManager.find(getDomainClass(),id);
    }

    @Override
    public T load(Serializable id) {
        return entityManager.getReference(getDomainClass(),id);
    }

    @Override
    public List<T> getAll() {
        return entityManager
                .createQuery("select t from "+getDomainClassName()+" t",getDomainClass())
                .getResultList();
    }

    @Override
    public void update(T t) {
        entityManager.persist(t);
    }

    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }

    @Override
    public void deleteById(Serializable id) {
        entityManager.remove(load(id));
    }

    @Override
    public void deleteAll() {
        entityManager.createNamedQuery("delete from "+getDomainClassName());
    }

    @Override
    public long count() {
        return entityManager
                .createQuery("select count(*) from "+getDomainClassName(),Long.class)
                .getSingleResult();
    }

    @Override
    public boolean exists(Serializable id) {
        return (get(id) != null);
    }
}
