package com.javarush.november.repository;


import com.javarush.november.entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Optional;

public class CityDAO extends GenericDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public Optional<City> getCityById(Integer id) {
        Query<City> query = getCurrentSession().createQuery("select c from City as c join fetch c.country " +
                "where c.id = :ID", City.class);
        query.setParameter("ID", id);
        try {
            City city = query.getSingleResult();
            return Optional.ofNullable(city);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
