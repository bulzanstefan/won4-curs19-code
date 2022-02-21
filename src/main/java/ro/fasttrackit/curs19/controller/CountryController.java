package ro.fasttrackit.curs19.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs19.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs19.model.Country;
import ro.fasttrackit.curs19.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    List<Country> getCountries() {
        return service.getAll();
    }

    @PostMapping
    Country addCountry(@RequestBody Country country) {
        return service.add(country);
    }

    @PutMapping("{id}")
    Country replaceCountry(@PathVariable int id, @RequestBody Country country) {
        return service.replace(id, country)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find country with id " + id));
    }

    @DeleteMapping("{id}")
    Country deleteCountry(@PathVariable int id) {
        return service.delete(id)
                .orElse(null);
    }
}
//-----------controller  : http interface
//-----------service     : application logic (business logic)
//-----------repository  : data storage
//-----------model       : data objects