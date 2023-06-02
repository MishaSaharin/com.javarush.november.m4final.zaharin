package com.javarush.november.sessionprovider;

import com.javarush.november.domain.City;
import com.javarush.november.domain.Country;
import com.javarush.november.domain.CountryLanguage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PropertiesSessionProvider implements SessionProvider {
    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .buildSessionFactory();
    }
}
