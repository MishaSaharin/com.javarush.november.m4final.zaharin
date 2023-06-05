package com.javarush.november;

import com.javarush.november.entity.City;
import com.javarush.november.redis.CityCountry;
import com.javarush.november.repository.ServiceDAO;

import java.util.List;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

public class App {
    public static void main(String[] args) {
        ServiceDAO serviceDao = new ServiceDAO();
        List<City> allCities = serviceDao.fetchData(serviceDao);
        List<CityCountry> preparedData = serviceDao.transformData(allCities);
        serviceDao.pushToRedis(preparedData);
        serviceDao.getSessionFactory().getCurrentSession().close();
        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);
        long startRedis = currentTimeMillis();
        serviceDao.testRedisData(ids);
        long stopRedis = currentTimeMillis();
        long startMysql = currentTimeMillis();
        serviceDao.testMysqlData(ids);
        long stopMysql = currentTimeMillis();
        out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));
        serviceDao.shutdown();
    }
}
