package com.javarush.november.dao;

import com.javarush.november.domain.CountryLanguage;
import org.hibernate.SessionFactory;

public class CountryLanguageDAO extends AbstractHibernateDao<CountryLanguage> {
    public CountryLanguageDAO(SessionFactory sessionFactory) {
        super(CountryLanguage.class, sessionFactory);
    }
}
