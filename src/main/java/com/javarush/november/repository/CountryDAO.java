package com.javarush.november.repository;

import com.javarush.november.entity.Country;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDAO implements GenericDAO<Country> {
    private final SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Country getById(int id) {
        return null;
    }

    @Override
    public List<Country> getItems(int offset, int count) {
        return null;
    }

    @Override
    public List<Country> getAll() {
        Query<Country> query = sessionFactory.getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class);
        return query.list();
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public void create(Country entity) {

    }

    @Override
    public void update(Country entity) {

    }

    @Override
    public void delete(Country entity) {

    }

    @Override
    public void deleteById(int entityId) {

    }
}
