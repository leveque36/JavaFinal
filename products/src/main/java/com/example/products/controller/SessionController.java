package com.example.products.controller;

import com.example.products.security.ProductsAutenticationReq;
import com.example.products.security.ProductsJWTUtilService;
import com.example.products.security.ProductsToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SessionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private ProductsJWTUtilService productsJWTUtilService;
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @PostMapping("/authenticate")
    public ResponseEntity<ProductsToken> authenticate(@RequestBody ProductsAutenticationReq productAuthenticationReq) {
        logger.info("Autenticando al usuario {}", productAuthenticationReq.getUsuario());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(productAuthenticationReq.getUsuario(), productAuthenticationReq.getClave()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(productAuthenticationReq.getUsuario());

        final String jwt = productsJWTUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new ProductsToken(jwt));
    }

}
