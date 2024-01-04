package io.hyao.userreglogindemo.service;

import io.hyao.userreglogindemo.component.UserDetailsMapper;
import io.hyao.userreglogindemo.entity.User;
import io.hyao.userreglogindemo.repository.JdbcUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    private final JdbcUserRepository jdbcUserRepository;
    private final UserDetailsMapper userDetailsMapper;

    public CustomUserService(JdbcUserRepository jdbcUserRepository, UserDetailsMapper userDetailsMapper) {
        this.jdbcUserRepository = jdbcUserRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userNAME) throws UsernameNotFoundException {
        User userCredentials = jdbcUserRepository.getUserInfoByUserName(userNAME);
        return userDetailsMapper.toUserDetails(userCredentials);
    }

}
