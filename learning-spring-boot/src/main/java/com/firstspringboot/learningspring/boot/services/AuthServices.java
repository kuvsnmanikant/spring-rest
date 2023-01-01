package com.firstspringboot.learningspring.boot.services;

import java.util.HashSet;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.firstspringboot.learningspring.boot.dto.LoginDto;
import com.firstspringboot.learningspring.boot.dto.RegisterUserDto;
import com.firstspringboot.learningspring.boot.entries.Roles;
import com.firstspringboot.learningspring.boot.entries.User;
import com.firstspringboot.learningspring.boot.exception.BlogAPIException;
import com.firstspringboot.learningspring.boot.repository.RoleRepository;
import com.firstspringboot.learningspring.boot.repository.userRepository;
import com.firstspringboot.learningspring.boot.security.JwtTokenProvider;
import com.firstspringboot.learningspring.boot.services.interfaces.AuthService;

@Service
public class AuthServices implements AuthService{

    private AuthenticationManager authenticationManager;
    private userRepository userrepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServices(AuthenticationManager authenticationManager, userRepository userrepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userrepository = userrepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private User mapToEntries(RegisterUserDto registerUserDto){

        User user = modelMapper.map(registerUserDto, User.class);
        return user;
    }
    @Override
    public String login(LoginDto loginDto) {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtTokenProvider.generateJwtToken(auth); // by including this we are generating the jwt-token 
       // return "User "+loginDto.getUsernameOrEmail()+" logged in successful"; // this is for basic auth success message
       return token;
    }


    @Override
    public String registerUser(RegisterUserDto registerUserDto) {

        if(userrepository.existsByUserName(registerUserDto.getUserName())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "User already exist");
        }
        if(userrepository.existsByEmail(registerUserDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "User email already exist");
        }
        User user = mapToEntries(registerUserDto);
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        Set<Roles> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER").get());
        user.setRoles(roles);
        userrepository.save(user);
        return "User registered successfully !!!";
    }
    
}
