package com.javarush.november.repository;


import com.javarush.november.entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class CityDAO implements GenericDAO<City> {
    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public City getById(int id) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City as c join fetch c.country " +
                "where c.id = :ID", City.class);
        query.setParameter("ID", id);
        try {
            City city = query.getSingleResult();
            return Optional.ofNullable(city).get();
        } catch (NoResultException e) {
            return (City) Optional.empty().get();
        }
    }

    @Override
    public List<City> getItems(int offset, int count) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c", City.class);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public int getTotalCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(c) from City c", Long.class);
        return query.uniqueResult().intValue();
    }

    @Override
    public void create(City entity) {

    }

    @Override
    public void update(City entity) {

    }

    @Override
    public void delete(City entity) {

    }

    @Override
    public void deleteById(int entityId) {

    }
}
