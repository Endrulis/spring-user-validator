package com.endrulis.validator.services;

import com.endrulis.validator.dto.UserDTO;
import com.endrulis.validator.dto.UserRegistrationDTO;
import com.endrulis.validator.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void saveUser( UserDTO userDto );

    UserEntity findUserByEmail( String email );

    List<UserDTO> findAllUsers();
}
