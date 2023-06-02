package com.javarush.november.domain;

import com.sun.istack.NotNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(schema = "world", name = "country")
public class Country {
    @Id
    @NotNull
    @ColumnDefault("0")
    private Integer id;
    @NotNull
    @ColumnDefault("''")
    @Column(name = "code", length = 3)
    private String code;
    @NotNull
    @ColumnDefault("''")
    @Column(name = "code_2", length = 2)
    private String codeSecond;
    @NotNull
    @ColumnDefault("''")
    @Column(name = "name", length = 52)
    private String name;
    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "continent")
    @ColumnDefault("0")
    private Continent continent;
    @NotNull
    @ColumnDefault("''")
    @Column(name = "region", length = 26)
    private String region;
    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "surface_area")
    private BigDecimal surfaceArea;
    @Column(name = "indep_year")
    @ColumnDefault("null")
    private Short independenceYear;
    @NotNull
    @Column(name = "population")
    @ColumnDefault("0")
    private Integer population;
    @ColumnDefault("null")
    @Column(name = "gnp")
    private BigDecimal gnp;
    @ColumnDefault("null")
    @Column(name = "gnpo_id")
    private BigDecimal gnpoId;
    @NotNull
    @ColumnDefault("''")
    @Column(name = "local_name", length = 45)
    private String localName;
    @NotNull
    @ColumnDefault("''")
    @Column(name = "government_form", length = 45)
    private String governmentForm;
    @ColumnDefault("null")
    @Column(name = "head_of_state", length = 60)
    private String headOfState;
    @OneToOne
    @ColumnDefault("null")
    @JoinColumn(name = "capital")
    private City capital;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Set<CountryLanguage> languages;

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeSecond() {
        return codeSecond;
    }

    public void setCodeSecond(String codeSecond) {
        this.codeSecond = codeSecond;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Short getIndependenceYear() {
        return independenceYear;
    }

    public void setIndependenceYear(Short independenceYear) {
        this.independenceYear = independenceYear;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public BigDecimal getGnp() {
        return gnp;
    }

    public void setGnp(BigDecimal gnp) {
        this.gnp = gnp;
    }

    public BigDecimal getGnpoId() {
        return gnpoId;
    }

    public void setGnpoId(BigDecimal gnpoId) {
        this.gnpoId = gnpoId;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public Set<CountryLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<CountryLanguage> languages) {
        this.languages = languages;
    }
}
