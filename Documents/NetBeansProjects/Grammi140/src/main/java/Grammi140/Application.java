package Grammi140;

import Grammi140.Models.User.Authoritie;
import Grammi140.Repository.Repositories.AuthoritieRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    AuthoritieRepository ar;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");

            }
        };
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (ar.findAll().size() <= 0) {
            Arrays.asList(new Authoritie("USER"), new Authoritie("ADMIN")).forEach(a -> ar.save(a));
        }

    }

}
