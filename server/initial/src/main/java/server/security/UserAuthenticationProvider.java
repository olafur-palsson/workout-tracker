package server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import server.data.user.UserEntity;
import server.data.user.UserRepository;

import java.util.ArrayList;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        UserEntity user = userRepository.findOne(email);
        String password = authentication.getCredentials().toString();
        System.out.println("AUTH PROVIDER");
        System.out.println("user = " + user.getEmail() + ":" + user.getPassword());
        System.out.println("Password inserted = " + password + " for name = " + email);
        if(user.getPassword().equals(password))
            return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
