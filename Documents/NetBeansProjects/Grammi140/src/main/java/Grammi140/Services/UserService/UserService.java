/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grammi140.Services.UserService;

import Grammi140.Impl.UserImpl;
import Grammi140.Models.User.Authoritie;
import Grammi140.Models.User.User;
import Grammi140.Repository.Repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
public class UserService implements UserImpl {

    @Autowired
    UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    @Override
    public boolean SignIn(User user, List< Authoritie> authorities) {
        if (userRepository.findByEmail(user.getEmail()) != null || authorities.size() <= 0) {
            return false;
        }
        encoder = new BCryptPasswordEncoder();
        user.setAuthoritieList(authorities);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

}
