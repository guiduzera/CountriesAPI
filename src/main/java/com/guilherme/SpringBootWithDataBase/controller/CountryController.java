package com.guilherme.SpringBootWithDataBase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.SpringBootWithDataBase.model.Country;
import com.guilherme.SpringBootWithDataBase.repository.CountryRepository;

@RestController
public class CountryController {
    
    @Autowired
    private CountryRepository repository;

    @GetMapping("/")
    public String populate() {
        repository.save(new Country("Brazil", "Brasilia", "+55"));
        repository.save(new Country("Argentina", "Buenos Aires", "+54"));
        repository.save(new Country("Chile", "Santiago", "+56"));
        repository.save(new Country("Uruguay", "Montevideo", "+598"));
        repository.save(new Country("Paraguay", "Asuncion", "+595"));
        return "Países populados com sucesso!";
    }
    
    @GetMapping("/countries")
    public List<Country> getCountries() {
        try {
            return (List<Country>) repository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
    
    @GetMapping("/countries/{id}")
    public Country getCountry(@PathVariable Long id) {
        try {
            return repository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }
    
    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return repository.save(country);
    }
    
    @PutMapping("/countries/{id}")
    public Country updateCountry(@RequestBody Country country, @PathVariable Long id) {
        Country countryToUpdate = repository.findById(id).get();
        countryToUpdate.setName(country.getName());
        countryToUpdate.setCapital(country.getCapital());
        countryToUpdate.setDdi(country.getDdi());
        return repository.save(countryToUpdate);
    }
    
    @DeleteMapping("/countries/{id}")
    public void deleteCountry(@PathVariable Long id) {
        repository.deleteById(id);
    }
}