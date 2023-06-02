package com.javarush.november.domain;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "world", name = "country_language")
public class CountryLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @NotNull
    @Column(name = "language", length = 30)
    @ColumnDefault("''")
    private String language;
    @NotNull
    @Column(name = "is_official", columnDefinition = "BIT")
    @ColumnDefault("false")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isOfficial;
    @NotNull
    @Column(name = "percentage")
    @ColumnDefault("0.0")
    private BigDecimal percentage;

    public CountryLanguage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
