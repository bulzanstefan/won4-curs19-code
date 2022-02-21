package ro.fasttrackit.curs19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.curs19.service.CountryProvider;

import java.util.List;

@SpringBootApplication
public class Curs19CodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Curs19CodeApplication.class, args);
    }

    //    @ConditionalOnProperty(name = "source", havingValue = "!file", matchIfMissing = true)
    @Bean
    CountryProvider emptyCountryProvider() {
        return List::of;
    }
}
