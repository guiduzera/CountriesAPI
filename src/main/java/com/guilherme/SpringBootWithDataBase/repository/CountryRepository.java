package com.guilherme.SpringBootWithDataBase.repository;

import org.springframework.data.repository.CrudRepository;

import com.guilherme.SpringBootWithDataBase.model.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
    
}
