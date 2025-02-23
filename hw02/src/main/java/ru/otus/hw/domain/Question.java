package ru.otus.hw.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {

    public String showAnswers() {
        if (answers == null || answers.isEmpty()) {
            return "";
        }

        StringBuilder answersAsList = new StringBuilder();
        int answerCounter = 1;
        for (Answer answer : answers) {
            answersAsList.append(answerCounter++).append(". ").append(answer.text()).append("\n");
        }

        return answersAsList.toString();
    }

}