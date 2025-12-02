package org.skypro.course2.service;

import org.skypro.course2.domain.Question;
import org.skypro.course2.exception.AmountNotValidException;
import org.skypro.course2.exception.AmountExceedsTotalException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0) {
            throw new AmountNotValidException("Количество вопросов должно быть больше 0");
        }

        Collection<Question> allQuestions = questionService.getAll();
        if (amount > allQuestions.size()) {
            throw new AmountExceedsTotalException(
                    "Запрошено " + amount + " вопросов, но доступно только " + allQuestions.size()
            );
        }

        List<Question> available = new ArrayList<>(allQuestions);
        Collections.shuffle(available);
        return available.subList(0, amount);
    }
}