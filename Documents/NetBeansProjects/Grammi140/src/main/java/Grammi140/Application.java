package Grammi140;

import Grammi140.Models.User.Authoritie;
import Grammi140.Models.User.User;
import Grammi140.Repository.Repositories.AuthoritieRepository;
import Grammi140.Repository.Repositories.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritieRepository authoritieRepository;
    private BCryptPasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * init some data
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        if (authoritieRepository.findAll().size() <= 0) {
            List<String> authorities = Arrays.asList("USER", "ADMIN");
            authorities.stream().forEach(str -> authoritieRepository.save(new Authoritie(str)));
        }
        if (userRepository.findAll().size() <= 0) {
            encoder = new BCryptPasswordEncoder();
            List<User> users = Stream
                    .of(
                            new User("user", "user", "user@gamil.com", encoder.encode("23051988"), authoritieRepository.findAll()),
                            new User("admin", "admin", "admin@gamil.com", encoder.encode("23051988"), authoritieRepository.findAll())
                    ).collect(Collectors.toList());
            users.stream().forEach(userRepository::save);

        }

    }

}
