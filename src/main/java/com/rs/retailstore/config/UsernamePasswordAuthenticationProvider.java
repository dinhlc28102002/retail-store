package com.rs.retailstore.config;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password =  authentication.getCredentials().toString();

        List<Customer> customers = customerRepository.findByUsername(username);

        if (CollectionUtils.isEmpty(customers)) {
            throw new BadCredentialsException("No customer registered with this username= " + username);
        }else {
            if (passwordEncoder.matches(password, customers.get(0).getPassword())) {
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
            }else {
                throw new BadCredentialsException("invalid password for username= " + username);
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
