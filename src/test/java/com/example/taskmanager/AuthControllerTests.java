package com.example.taskmanager;
import com.example.taskmanager.controller.AuthControllerAPI;
import com.example.taskmanager.dto.LoginRequest;
import com.example.taskmanager.dto.LoginResponse;
import com.example.taskmanager.security.JwtServiceAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtServiceAPI jwtService;

    @InjectMocks
    private AuthControllerAPI authController;

    @Test
    void login_shouldReturnToken_whenCredentialsAreValid() {
        LoginRequest request = new LoginRequest();
        request.setUsername("kwame");
        request.setPassword("password123");

        User userDetails = new User(
                "kwame",
                "encodedPassword",
                Collections.emptyList()
        );

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        /*when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);*/
        doReturn(authentication)
                .when(authenticationManager)
                .authenticate(any());

        /*when(jwtService.generateToken(any(org.springframework.security.core.userdetails.UserDetails.class)))
                .thenReturn("fake-jwt-token");*/
        doReturn("fake-jwt-token")
                .when(jwtService)
                .generateToken(any());

        LoginResponse response = authController.login(request).getBody();

        assertEquals("fake-jwt-token", response.getToken());
    }
}
