package com.isaachome.blog.controller;

import com.isaachome.blog.entity.Role;
import com.isaachome.blog.entity.User;
import com.isaachome.blog.payload.JwtAuthResponseDto;
import com.isaachome.blog.payload.LoginDto;
import com.isaachome.blog.payload.SignUpDto;
import com.isaachome.blog.repos.RoleRepo;
import com.isaachome.blog.repos.UserRepo;
import com.isaachome.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired(required = true)
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDto){
      Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token from provider
        String token = jwtTokenProvider.generateToken(authentication);
        return   ResponseEntity.ok(new JwtAuthResponseDto(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        // check username availability
        if(userRepo.existsByUsername(signUpDto.getUsername())){
            return  new ResponseEntity<>("Username is already taken",HttpStatus.BAD_REQUEST);
        }
        // check email availability
        if(userRepo.existsByEmail(signUpDto.getEmail())){
            return  new ResponseEntity<>("Email is already taken",HttpStatus.BAD_REQUEST);
        }
        // create user
        User user=new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepo.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(role));
        userRepo.save(user);
        return  new ResponseEntity<>("User register successfully.",HttpStatus.OK);
    }
}
