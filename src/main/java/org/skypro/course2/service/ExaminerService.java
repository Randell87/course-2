package org.skypro.course2.service;

import org.skypro.course2.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}