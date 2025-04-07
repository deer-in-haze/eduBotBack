package com.techchicks.edubot.service;

import com.techchicks.edubot.model.User;
import com.techchicks.edubot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Long id, User user) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setName(user.getName());
            existingUser.setJoinedDate(user.getJoinedDate());
            existingUser.setHighscore(user.getHighscore());
            userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public void deleteUser(Long id) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            userRepository.delete(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public void updateUserHighscore(Long userId, Long newHighscore) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setHighscore(newHighscore);
            userRepository.save(user);
        }
    }

    public List<User> getTop10Users() {
        return userRepository.findTop10ByOrderByHighscoreDesc();
    }
}



