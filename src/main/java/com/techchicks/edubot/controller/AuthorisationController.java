package com.techchicks.edubot.controller;

import com.techchicks.edubot.model.User;
import com.techchicks.edubot.model.LoginCredentials;
import com.techchicks.edubot.repository.UserRepository;
import com.techchicks.edubot.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthorisationController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> registerHandler(
            @RequestBody User user
    ){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user.setJoinedDate(Instant.now());
        user.setHighscore(0L);
        user = userRepo.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("jwt-token",token);
    }

    @PostMapping("/login")
    public Map<String,Object> loginHandler(
            @RequestBody LoginCredentials body
    ){
        try{
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getUsername());
            return Map.of("jwt-token",token);
        } catch(AuthenticationException authExc){
            throw new RuntimeException("Invalid username/password.");
        }

    }

}