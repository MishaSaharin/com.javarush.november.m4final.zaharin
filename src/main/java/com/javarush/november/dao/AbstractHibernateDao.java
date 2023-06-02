package com.javarush.november.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public abstract class AbstractHibernateDao<T> {
    private final Class<T> aClass;
    private SessionFactory sessionFactory;

    public AbstractHibernateDao(final Class<T> aClass, SessionFactory sessionFactory) {
        this.aClass = aClass;
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T getById(final Integer id) {
        return (T) getCurrentSession().get(aClass, id);
    }

    public List<T> getItems(int offset, int count) {
        String hql = "FROM ";
        Query query = getCurrentSession().createQuery(hql + aClass.getName(), aClass);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll() {
        String hql = "FROM ";
        Query query = getCurrentSession().createQuery(hql + aClass.getName(), aClass);
        return query.list();
    }

    public T saveOrUpdate(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final Integer entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }
}
