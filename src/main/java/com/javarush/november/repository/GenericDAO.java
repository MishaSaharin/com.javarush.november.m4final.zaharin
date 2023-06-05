package com.javarush.november.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public abstract class GenericDAO<T> {
    private final SessionFactory sessionFactory;
    private final Class<T> aClass;

    protected GenericDAO(final Class<T> aClass, SessionFactory sessionFactory) {
        this.aClass = aClass;
        this.sessionFactory = sessionFactory;
    }

    public List<T> findAll() {
        String query = "select c from " + aClass.getName() + " as c join fetch c.languages";
        return getCurrentSession()
                .createQuery(query, aClass)
                .list();
    }

    public List<T> getItems(int offset, int limit) {
        Query<T> query = getCurrentSession()
                .createQuery("from " + aClass.getName(), aClass)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return query.list();
    }

    public int getTotalCount() {
        Query<Long> query = getCurrentSession()
                .createQuery("select count(c) from " + aClass.getName() + " c", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
