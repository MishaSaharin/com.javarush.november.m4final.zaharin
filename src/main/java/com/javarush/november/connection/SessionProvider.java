package com.javarush.november.connection;

import org.hibernate.SessionFactory;

public interface SessionProvider {
    SessionFactory getSessionFactory();
}
