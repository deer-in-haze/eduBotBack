package com.techchicks.edubot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long id;

    @Column(name = "continent", length = 100)
    private String continent;

    @Column(name = "difficulty", length = 50)
    private String difficulty;

    @Lob
    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answers")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> answers;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "points")
    private Long points;

}