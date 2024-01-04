package io.hyao.userreglogindemo.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import java.io.IOException;
import java.util.Base64;
public class MyBasicAuthenticationFilter extends BasicAuthenticationFilter {
    public MyBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Basic")) {
            String encodeCredentials = header.substring(6);
            byte[] decodeBytes = Base64.getDecoder().decode(encodeCredentials);
            String credentials = new String(decodeBytes);

            String[] parts = credentials.split(":", 2);
            String username = parts[0];
            String password = parts[1];
            System.out.println("Received Username: " + username + " Received Password: " + password);

        }
        chain.doFilter(request, response);
    }
}
