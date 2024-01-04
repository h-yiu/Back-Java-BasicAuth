package io.hyao.userreglogindemo.component;

import io.hyao.userreglogindemo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDetailsMapper {
    public UserDetails toUserDetails(User user) {
        user.setRoles(Set.of("USER"));
        return org.springframework.security.core.userdetails.User.withUsername(user.getName())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(String[]::new))
                .build();
    }
}
