package com.it.different.courses.internetprovider.services;

import com.it.different.courses.internetprovider.persistence.entity.Role;
import com.it.different.courses.internetprovider.persistence.entity.Role.RoleType;
import com.it.different.courses.internetprovider.persistence.entity.User;
import com.it.different.courses.internetprovider.persistence.repository.RoleRepository;
import com.it.different.courses.internetprovider.persistence.repository.UserRepository;
import com.it.different.courses.internetprovider.services.dto.JwtResponseDTO;
import com.it.different.courses.internetprovider.services.dto.LoginRequestDTO;
import com.it.different.courses.internetprovider.services.dto.SignupRequestDTO;
import com.it.different.courses.internetprovider.services.exceptions.RoleNotExistException;
import com.it.different.courses.internetprovider.services.exceptions.UsernameAlreadyExistException;
import com.it.different.courses.internetprovider.services.util.JwtUtils;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public void signup(SignupRequestDTO createUserDto) {
        Role role = roleRepository.findByName(RoleType.ROLE_CUSTOMER)
            .orElseThrow(() -> new RoleNotExistException(String.format("Role %s not exit", RoleType.ROLE_CUSTOMER)));
        if(userRepository.count() == 0){
            role = roleRepository.findByName(RoleType.ROLE_ADMIN)
                .orElseThrow(() -> new RoleNotExistException(String.format("Role %s not exit", RoleType.ROLE_ADMIN)));
        }
        if(userRepository.findByUsername(createUserDto.getUsername()).isPresent()){
            throw new UsernameAlreadyExistException(String.format("Username %s already exist", createUserDto.getUsername()));
        }
        User user = new User(createUserDto.getUsername(), passwordEncoder.encode(createUserDto.getPassword()), createUserDto.getEmail(),
            Set.of(role));
        userRepository.save(user);
    }

    public JwtResponseDTO signin(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new JwtResponseDTO(jwt);
    }
}