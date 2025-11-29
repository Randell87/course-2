package org.skypro.course2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.course2.domain.Question;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

    private ExaminerServiceImpl examinerService;
    private QuestionService questionServiceMock;

    @BeforeEach
    void setUp() {
        questionServiceMock = mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(questionServiceMock);
    }

    @Test
    void getQuestions_shouldThrowForZeroOrNegativeAmount() {
        assertThatThrownBy(() -> examinerService.getQuestions(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> examinerService.getQuestions(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getQuestions_shouldThrowWhenAmountExceedsAvailable() {
        when(questionServiceMock.getAll()).thenReturn(Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2")
        ));

        assertThatThrownBy(() -> examinerService.getQuestions(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getQuestions_shouldReturnCorrectAmount() {
        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q2", "A2");
        when(questionServiceMock.getAll()).thenReturn(Set.of(q1, q2));

        Collection<Question> result = examinerService.getQuestions(2);

        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(q1, q2);
    }
}