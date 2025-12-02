package org.skypro.course2.service;

import org.skypro.course2.domain.Question;
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
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }

        Collection<Question> allQuestions = questionService.getAll();
        if (amount > allQuestions.size()) {
            throw new IllegalArgumentException(
                    "Запрошено " + amount + " вопросов, но доступно только " + allQuestions.size()
            );
        }

        Set<Question> result = new LinkedHashSet<>();
        List<Question> available = new ArrayList<>(allQuestions);

        // Перемешиваем и берём первые N — эффективнее, чем цикл с getRandom
        Collections.shuffle(available);
        result.addAll(available.subList(0, amount));

        return result;
    }
}