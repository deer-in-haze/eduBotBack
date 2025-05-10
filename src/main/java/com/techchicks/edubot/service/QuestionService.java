package com.techchicks.edubot.service;

import com.techchicks.edubot.model.Question;
import com.techchicks.edubot.repository.QuestionRepository;
import java.util.Collections;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    public void createQuestionsBulk(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    public void updateQuestion(Long id, Question question) {
        Optional<Question> existingQuestionOpt = questionRepository.findById(id);
        if (existingQuestionOpt.isPresent()) {
            Question existingQuestion = existingQuestionOpt.get();
            existingQuestion.setContinent(question.getContinent());
            existingQuestion.setDifficulty(question.getDifficulty());
            existingQuestion.setQuestion(question.getQuestion());
            existingQuestion.setAnswers(question.getAnswers());
            existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
            existingQuestion.setPoints(question.getPoints());
            questionRepository.save(existingQuestion);
        } else {
            throw new RuntimeException("Question not found with id " + id);
        }
    }

    public void deleteQuestion(Long id) {
        Optional<Question> existingQuestionOpt = questionRepository.findById(id);
        if (existingQuestionOpt.isPresent()) {
            Question existingQuestion = existingQuestionOpt.get();
            questionRepository.delete(existingQuestion);
        } else {
            throw new RuntimeException("Question not found with id " + id);
        }
    }

    public List<Question> getQuestionsForQuiz(String continent, String difficulty) {
        List<Question> questions;
        if (Objects.equals(continent, "ALL CONTINENTS")) {
            questions =  questionRepository.findByDifficulty(difficulty);
        } else {
            questions = questionRepository.findByContinentAndDifficulty(continent, difficulty);
        }

        Collections.shuffle(questions);

        return questions.subList(0, Math.min(10, questions.size()));
    }


}
