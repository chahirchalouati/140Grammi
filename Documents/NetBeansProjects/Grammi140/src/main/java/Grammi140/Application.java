package Grammi140;

import Grammi140.Models.User.Authoritie;
import Grammi140.Repository.Repositories.AuthoritieRepository;
import Grammi140.Repository.Repositories.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritieRepository authoritieRepository;

    @Override
    public void run(String... args) throws Exception {
        if (authoritieRepository.findAll().size() <= 0) {
            List<String> authorities = Arrays.asList("USER", "ADMIN");
            authorities.stream().forEach(str -> authoritieRepository.save(new Authoritie(str)));
        }

    }

}
