package com.example.projectintern.controller;

import com.example.projectintern.config.JwtTokenUtil;
import com.example.projectintern.dto.account.LoginRequest;
import com.example.projectintern.dto.account.LoginResponse;
import com.example.projectintern.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AccountEndPoint {

    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";

    @Autowired
    private IAccountService accountService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private AuthenticationManager authenticationManager;
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        UserDetails userDetails;
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        userDetails = accountService.loadUserByUsername(request.getUsername());
        String jwt = jwtTokenUtil.generateToken(userDetails);
        response.setJwt(jwt);
        return response;
    }

}
