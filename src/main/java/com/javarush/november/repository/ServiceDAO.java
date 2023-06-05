package com.javarush.november.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.javarush.november.connection.RedisClientSessionFactory;
import com.javarush.november.connection.RelationalDbSessionFactory;
import com.javarush.november.entity.City;
import com.javarush.november.entity.Country;
import com.javarush.november.entity.CountryLanguage;
import com.javarush.november.redis.CityCountry;
import com.javarush.november.redis.Language;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class ServiceDAO {
    private final SessionFactory sessionFactory;
    private final RedisClient redisClient;
    private final ObjectMapper objectMapper;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;

    public ServiceDAO() {
        sessionFactory = new RelationalDbSessionFactory().getPrepareRelationalDb();
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        redisClient = RedisClientSessionFactory.prepareRedisClient();
        objectMapper = new ObjectMapper();
    }

    public List<City> fetchData(ServiceDAO serviceDao) {
        try (Session session = serviceDao.sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();
            List<Country> countries = serviceDao.countryDAO.findAll();
            int totalCount = serviceDao.cityDAO.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(serviceDao.cityDAO.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }

    public List<CityCountry> transformData(List<City> cities) {
        return cities.stream().map(city -> {
            CityCountry cityCountry = new CityCountry();
            cityCountry.setId(city.getId());
            cityCountry.setName(city.getName());
            cityCountry.setDistrict(city.getDistrict());
            cityCountry.setPopulation(city.getPopulation());
            Country country = city.getCountry();
            cityCountry.setCountryCode(country.getCode());
            cityCountry.setAlternativeCountryCode(country.getAlternativeCode());
            cityCountry.setCountryName(country.getName());
            cityCountry.setContinent(country.getContinent());
            cityCountry.setCountryRegion(country.getRegion());
            cityCountry.setCountrySurfaceArea(country.getSurfaceArea());
            cityCountry.setCountryPopulation(country.getPopulation());
            Set<CountryLanguage> countryLanguages = country.getLanguages();
            Set<Language> languages = countryLanguages.stream().map(lng -> {
                Language language = new Language();
                language.setLanguage(lng.getLanguage());
                language.setOfficial(lng.getOfficial());
                language.setPercentage(lng.getPercentage());
                return language;
            }).collect(Collectors.toSet());
            cityCountry.setLanguages(languages);
            return cityCountry;
        }).collect(Collectors.toList());
    }

    public void pushToRedis(List<CityCountry> data) {
        try (StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            RedisCommands<String, String> sync = connect.sync();
            for (CityCountry cityCountry : data) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), objectMapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void testMysqlData(List<Integer> ids) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            for (Integer id : ids) {
                Optional<City> cityOptional = cityDAO.getCityById(id);
                Set<CountryLanguage> languages = cityOptional.get().getCountry().getLanguages();
            }
            session.getTransaction().commit();
        }
    }

    public void testRedisData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            RedisCommands<String, String> sync = connect.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    objectMapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
        if (nonNull(redisClient)) {
            redisClient.shutdown();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
