package org.mobilehub.identity.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mobilehub.identity.dto.request.RegisterUserRequest;
import org.mobilehub.identity.dto.response.UserResponse;
import org.mobilehub.identity.entity.User;
import org.mobilehub.identity.exception.UserException;
import org.mobilehub.identity.mapper.UserMapper;
import org.mobilehub.identity.repository.RoleRepository;
import org.mobilehub.identity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleRepository roleRepository;

    public UserResponse register(RegisterUserRequest  registerUserRequest) {
        User user = userMapper.toUser(registerUserRequest);
        //user.setPassword(passwordEncoder.encode(user.getPasswordHash()));

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new UserException("user is invalid")));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
