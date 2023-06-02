package com.javarush.november.dao;

import com.javarush.november.domain.City;
import org.hibernate.SessionFactory;

public class CityDAO extends AbstractHibernateDao<City>{
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
