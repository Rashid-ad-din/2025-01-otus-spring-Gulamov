package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void execute() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        List<Question> questionList = questionDao.findAll();
        for (Question question : questionList) {
            ioService.printLine(question.text());
            ioService.printFormattedLine(question.showAnswers());
        }

    }
}