/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grammi140.Controllers;

import Grammi140.JWT.JwtResponse;
import Grammi140.JWT.JwtUtils;
import Grammi140.JWT.LoginRequest;
import Grammi140.Models.User.Authoritie;
import Grammi140.Models.User.User;
import Grammi140.Repository.Repositories.AuthoritieRepository;
import Grammi140.Repository.Repositories.UserRepository;
import Grammi140.Services.UserService.UserService;
import Grammi140.Utilities.Apierror;
import java.util.Arrays;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritieRepository authoritieRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        String p = authentication.getName();
        User user = userRepository.findByEmail(p);

        return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getAuthoritieList()));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> postAsUser(@RequestBody @Valid User user) {

        Authoritie autoritie = authoritieRepository.findByAuthoritie("USER");

        boolean x = userService.SignIn(user, Arrays.asList(autoritie));
        if (x) {
            return new ResponseEntity<>(new Apierror("User created ", new Date(), HttpStatus.CREATED), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new Apierror("User already exist", new Date(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }
}
