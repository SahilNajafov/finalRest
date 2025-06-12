package com.finalprojectrest.controller;

import com.finalprojectrest.config.JwtService;
import com.finalprojectrest.dto.request.AuthenticationRequest;
import com.finalprojectrest.dto.response.AuthenticationResponse;
import com.finalprojectrest.entity.Student;
import com.finalprojectrest.repository.StudentRepository;
import com.finalprojectrest.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    JwtService jwtService;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    StudentRepository studentRepository;
    LogoutService logoutService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid Authorization Header");
        }

        String token = authHeader.substring(7);
        long expiration = jwtService.getTokenExpiration(token);

        logoutService.logout(token, expiration);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/set-password/{fin}")
    public ResponseEntity<?> setPassword(@RequestBody AuthenticationRequest request,@PathVariable String fin) {
        Student student = studentRepository.findByStudentFIN(fin).orElseThrow(()->new RuntimeException("Student not found"));
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setEmail(request.getEmail());
        studentRepository.save(student);
        return ResponseEntity.ok().build();
    }
}
