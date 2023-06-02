package com.javarush.november.dao;

import com.javarush.november.domain.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends AbstractHibernateDao<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
