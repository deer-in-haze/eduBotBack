package com.techchicks.edubot.controller;

import com.techchicks.edubot.model.Question;
import com.techchicks.edubot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/eduBot")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions;
        try {
            questions = questionService.getAllQuestions();
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            log.error("Error fetching questions: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/question")
    public ResponseEntity<Void> createQuestion(@RequestBody Question question) {
        questionService.createQuestion(question);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/questions/bulk")
    public ResponseEntity<Void> createQuestionsBulk(@RequestBody List<Question> questions) {
        questionService.createQuestionsBulk(questions);
        return ResponseEntity.noContent().build();
    }





    @PutMapping("question/{id}")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        questionService.updateQuestion(id, question);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("question/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/questions/{continent}/{difficulty}")
    public ResponseEntity<List<Question>> getQuestionsForQuiz(@PathVariable String continent, @PathVariable String difficulty) {
        List<Question> questions;
        try {
            questions = questionService.getQuestionsForQuiz(continent, difficulty);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            log.error("Error fetching questions: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
