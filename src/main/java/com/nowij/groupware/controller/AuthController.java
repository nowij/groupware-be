package com.nowij.groupware.controller;

import com.nowij.groupware.dto.AuthDto;
import com.nowij.groupware.dto.LoginDto;
import com.nowij.groupware.model.EmployeeEntity;
import com.nowij.groupware.repository.EmployeeRepository;
import com.nowij.groupware.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final EmployeeRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmployeeId(), loginDto.getUserPasswd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // token 생성
        String token = jwtGenerator.generateToken(authentication);
        AuthDto authDto = new AuthDto(token);
        authDto.setEmployeeId(loginDto.getEmployeeId());

        Optional<EmployeeEntity> optional = repository.findByEmployeeId(loginDto.getEmployeeId());
        if (optional.isPresent()) {
            EmployeeEntity employee = optional.get();
            authDto.setDeptCode(employee.getDepartments().get(0).getDeptCode());
        }

        return new ResponseEntity<>(authDto, HttpStatus.OK);
    }

}
