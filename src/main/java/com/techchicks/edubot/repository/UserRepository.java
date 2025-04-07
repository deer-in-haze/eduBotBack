package com.techchicks.edubot.repository;

import com.techchicks.edubot.model.Question;
import com.techchicks.edubot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findTop10ByOrderByHighscoreDesc();
}
