package com.example.proveedores.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProveedoresUsersDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = getById(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return User
                .withUsername(username)
                .password(usuario.password())
                .roles(usuario.roles().toArray(new String[0]))
                .build();
    }

    public record Usuario(String username, String password, Set<String> roles) {
    }
    public static Usuario getById(String username) {

        var password = "$2a$10$mo387.s562xdZklsQDX9O.IFBvSpMz8bRZktwoPNxCoJ8080KD8ay";
        Usuario user = new Usuario(
                "lvq",
                password,
                Set.of("USER")
        );
        var usuarios = List.of(user);

        return usuarios
                .stream()
                .filter(e -> e.username().equals(username))
                .findFirst()
                .orElse(null);
    }
}
