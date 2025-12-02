package org.skypro.course2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.course2.domain.Question;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService service;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService();
    }

    @Test
    void add_shouldAddNewQuestion() {
        Question q = service.add("Q1", "A1");
        assertThat(q.getQuestion()).isEqualTo("Q1");
        assertThat(q.getAnswer()).isEqualTo("A1");
    }

    @Test
    void add_shouldNotDuplicate() {
        service.add("Q1", "A1");
        service.add("Q1", "A1");
        assertThat(service.getAll()).hasSize(1);
    }

    @Test
    void remove_shouldRemoveExisting() {
        service.add("Q1", "A1");
        Question removed = service.remove("Q1", "A1");
        assertThat(removed).isNotNull();
        assertThat(service.getAll()).isEmpty();
    }

    @Test
    void getRandomQuestion_shouldReturnFromSet() {
        service.add("Q1", "A1");
        service.add("Q2", "A2");
        Question random = service.getRandomQuestion();
        assertThat(random).isNotNull();
        assertThat(service.getAll()).contains(random);
    }

    @Test
    void getAll_shouldBeUnmodifiable() {
        service.add("Q1", "A1");
        Collection<Question> all = service.getAll();
        assertThatThrownBy(() -> all.add(new Question("x", "y")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}