package com.javarush.november;

import com.javarush.november.dao.CityDAO;
import com.javarush.november.dao.CountryDAO;
import com.javarush.november.dao.CountryLanguageDAO;
import com.javarush.november.sessionprovider.PropertiesSessionProvider;
import com.javarush.november.sessionprovider.SessionProvider;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionProvider sessionProvider = new PropertiesSessionProvider();
        SessionFactory sessionFactory = sessionProvider.getSessionFactory();

        CityDAO cityDAO = new CityDAO(sessionFactory);
        CountryDAO countryDAO = new CountryDAO(sessionFactory);
        CountryLanguageDAO countryLanguageDAO = new CountryLanguageDAO(sessionFactory);
    }
}