package test;

import model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaok on 2017-02-03.
 */

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static final String BASE_URL = "http://www.omdbapi.com/";
    // http://www.omdbapi.com/?t=Star+wars&y=&plot=short&r=json

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {

        Map<String,String> parameters = new HashMap<>();
        parameters.put("t", "Star wars");
        parameters.put("r", "json");
        parameters.put("plot", "short");
        parameters.put("y", "");

        return args -> {
            Movie movie = restTemplate.getForObject(
                    "http://www.omdbapi.com/?t={t}&y={y}&plot={plot}&r={r}", Movie.class, parameters);
            log.info(movie.toString());
        };
    }


}
