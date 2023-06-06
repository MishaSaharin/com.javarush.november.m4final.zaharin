package com.javarush.november;

import com.javarush.november.connection.liquibase.Validator;
import com.javarush.november.entity.City;
import com.javarush.november.redis.CityCountry;
import com.javarush.november.repository.ServiceDAO;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static java.lang.System.out;

public class App {
    public static void main(String[] args) {
        Validator validator = new Validator();
        validator.getConnection();
        ServiceDAO serviceDao = new ServiceDAO();
        List<City> allCities = serviceDao.fetchData(serviceDao);
        List<CityCountry> preparedData = serviceDao.transformData(allCities);
        serviceDao.pushToRedis(preparedData);
        serviceDao.getSessionFactory().getCurrentSession().close();
        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);
        Instant instantStartRedis = Instant.now();
        serviceDao.testRedisData(ids);
        Instant instantStopRedis = Instant.now();
        Instant instantStartMysql = Instant.now();
        serviceDao.testMysqlData(ids);
        Instant instantStopMysql = Instant.now();
        long nanoRedis = Duration.between(instantStartRedis, instantStopRedis).getNano();
        long nanoMysql = Duration.between(instantStartMysql, instantStopMysql).getNano();
        out.printf("%s:\t%d ms\n", "Duration Redis", nanoRedis/1_000_000);
        out.printf("%s:\t%d ms\n", "Duration MySQL", nanoMysql/1_000_000);
        serviceDao.shutdown();
    }
}
