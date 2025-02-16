package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Value("${test.pass.value}")
    private int minScore;

    //TODO. Необходимо реализовать Unit тестирование
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String studentName = studentName(scanner);
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below (number of answer).%n");

        int score = 0;
        List<Question> questionList = questionDao.findAll();
        for (Question question : questionList) {
            int currentScore = processQuestion(question, scanner);
            score += currentScore;
        }
        if (score < minScore) {
            ioService.printLine("You didn't pass. Your score is lower than the minimum score.");
        } else {
            ioService.printFormattedLine(
                    "Congratulations, %s, you passed the test!",
                    studentName
            );
        }
    }

    private String studentName(Scanner scanner) {
        ioService.printLine("Enter your name");
        String name = scanner.nextLine();
        ioService.printLine("Enter your surname");
        String surname = scanner.nextLine();
        return name + " " + surname;
    }

    private int processQuestion(Question question, Scanner scanner) {
        ioService.printLine(question.text());
        ioService.printFormattedLine(question.showAnswers());
        List<Answer> answersList = question.answers();
        int answerValue = 0;
        ioService.printLine("Your answer is: ");
        while (true) {
            try {
                int answer = Integer.parseInt(scanner.nextLine());
                if (answer <= 0 || answer > answersList.size()) {
                    wrongAnswer(answersList.size());
                    continue;
                }
                answerValue = checkAnswer(answersList, answer);
            } catch (NumberFormatException e) {
                wrongAnswer(answersList.size());
                continue;
            }
            ioService.printLine("");
            return answerValue;
        }
    }

    private int checkAnswer(List<Answer> answers, int answer) {
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).isCorrect()) {
                if (answer == i + 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private void wrongAnswer(int size) {
        ioService.printFormattedLine(
                "Invalid answer. Enter a number between 1 and %d",
                size
        );
    }
}