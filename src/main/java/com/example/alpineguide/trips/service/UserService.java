package com.example.alpineguide.trips.service;

import com.example.alpineguide.trips.dao.User;
import com.example.alpineguide.trips.dto.UserDTO;
import com.example.alpineguide.trips.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO.name());
        return userRepository.save(user);
    }

    public Optional<User> findUser(Integer userId) {
        return userRepository.findById(userId);
    }

}
