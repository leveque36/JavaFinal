package com.example.orders.controller;

import com.example.orders.security.OrdersAutenticationReq;
import com.example.orders.security.OrdersJWTUtilService;
import com.example.orders.security.OrdersToken;
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
    private OrdersJWTUtilService ordersJWTUtilService;
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @PostMapping("/authenticate")
    public ResponseEntity<OrdersToken> authenticate(@RequestBody OrdersAutenticationReq ordersAuthenticationReq) {
        logger.info("Autenticando al usuario {}", ordersAuthenticationReq.getUsuario());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(ordersAuthenticationReq.getUsuario(), ordersAuthenticationReq.getClave()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(ordersAuthenticationReq.getUsuario());

        final String jwt = ordersJWTUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new OrdersToken(jwt));
    }
}
