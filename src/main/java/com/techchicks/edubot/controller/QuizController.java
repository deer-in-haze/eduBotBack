package com.techchicks.edubot.controller;

import com.techchicks.edubot.model.Quiz;
import com.techchicks.edubot.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/eduBot")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/quiz")
    public ResponseEntity<Void> createQuiz(@RequestBody Quiz quiz) {
        quizService.createQuiz(quiz);
        return ResponseEntity.noContent().build();
    }
}
