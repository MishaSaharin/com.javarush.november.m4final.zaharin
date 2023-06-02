package com.javarush.november.sessionprovider;

import org.hibernate.SessionFactory;

public interface SessionProvider {
    SessionFactory getSessionFactory();
}
