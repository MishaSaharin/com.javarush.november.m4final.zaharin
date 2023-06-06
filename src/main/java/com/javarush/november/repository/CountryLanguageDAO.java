package com.javarush.november.repository;

import com.javarush.november.entity.CountryLanguage;
import org.hibernate.SessionFactory;

public class CountryLanguageDAO extends GenericDAO<CountryLanguage>{
    public CountryLanguageDAO(Class<CountryLanguage> aClass, SessionFactory sessionFactory) {
        super(CountryLanguage.class, sessionFactory);
    }
}
