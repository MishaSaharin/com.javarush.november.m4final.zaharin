package com.javarush.november.domain;


import com.sun.istack.NotNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(schema = "world", name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @ColumnDefault("''")
    @Column(name = "name", length = 35)
    private String name;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @NotNull
    @ColumnDefault("''")
    @Column(name = "district", length = 20)
    private String district;
    @NotNull
    @ColumnDefault("0")
    @Column(name = "population")
    private Integer population;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
