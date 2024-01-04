package io.hyao.userreglogindemo.controller;

import io.hyao.userreglogindemo.entity.User;
import io.hyao.userreglogindemo.repository.JdbcUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    private final JdbcUserRepository jdbcUserRepository;

    private final PasswordEncoder passwordEncoder;

    public UserController(JdbcUserRepository jdbcUserRepository, PasswordEncoder passwordEncoder) {
        this.jdbcUserRepository = jdbcUserRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/reg")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Map<String,String>> registration(@RequestBody User user) {
        System.out.println(user);
        User userCredential = User.builder()
                .name(user.getName())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Set.of("USER"))
                .build();
        String newId = jdbcUserRepository.registerNewUser(userCredential);
        Map<String, String> res = new HashMap<>();

        if (newId != null) {
            res.put("result", "Register successfully.");
            return ResponseEntity.ok(res);
        }
        res.put("result", "There is an error, please try again");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login () {
        System.out.println("Authorized successfully" );
        Map<String, String> res = new HashMap<>();
        res.put("result", "Login successfully");
        return ResponseEntity.ok(res);
    }

}
