package com.javarush.november.repository;

import com.javarush.november.entity.CountryLanguage;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryLanguageDAO implements GenericDAO<CountryLanguage> {

    @Override
    public CountryLanguage getById(int id) {
        return null;
    }

    @Override
    public List<CountryLanguage> getItems(int offset, int count) {
        return null;
    }

    @Override
    public List<CountryLanguage> getAll() {
        return null;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public void create(CountryLanguage entity) {

    }

    @Override
    public void update(CountryLanguage entity) {

    }

    @Override
    public void delete(CountryLanguage entity) {

    }

    @Override
    public void deleteById(int entityId) {

    }
}
