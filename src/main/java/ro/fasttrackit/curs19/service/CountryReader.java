package ro.fasttrackit.curs19.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs19.model.Country;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component // "countryReader"
//@ConditionalOnProperty(name = "source", havingValue = "file")
public class CountryReader implements CountryProvider {
    @Override
    public List<Country> getCountries() {
        class Contor {
            private int id = 0;
        }
        //mai bine cu AtomicInteger
        try {
            Contor id = new Contor();
            return Files.lines(Path.of("src/main/resources/countries2.txt"))
                    .map(line -> lineToCountry(id.id++, line))
                    .collect(toList());
        } catch (IOException e) {
            System.err.println("Could not find find");
            return List.of();
        }
    }

    private Country lineToCountry(int id, String line) {
        String[] countryParts = line.split("\\|");

        return new Country(id, countryParts[0], countryParts[1], Long.parseLong(countryParts[2]),
                Double.parseDouble(countryParts[3]), countryParts[4], countryParts.length > 5 ? parseNeighbours(countryParts[5]) : List.of());
    }

    private List<String> parseNeighbours(String countryPart) {
        return Arrays.stream(countryPart.split("~"))
                .collect(toList());
    }
}