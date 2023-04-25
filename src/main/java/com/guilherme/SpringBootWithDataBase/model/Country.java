package com.guilherme.SpringBootWithDataBase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Country {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String capital;
    private String ddi;

    public Country() {
        super();
    }
    
    public Country(String name, String capital, String ddi) {
        super();
        this.name = name;
        this.capital = capital;
        this.ddi = ddi;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCapital() {
        return capital;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    public String getDdi() {
        return ddi;
    }
    public void setDdi(String ddi) {
        this.ddi = ddi;
    }
}
