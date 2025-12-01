package org.skypro.course2.service;

import org.skypro.course2.domain.Question;

import java.util.*;

public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new LinkedHashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(String question, String answer) {
        Question toRemove = new Question(question, answer);
        if (questions.remove(toRemove)) {
            return toRemove;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        List<Question> list = new ArrayList<>(questions);
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }
}