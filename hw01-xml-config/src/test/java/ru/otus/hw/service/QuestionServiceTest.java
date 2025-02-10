package ru.otus.hw.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionDao questionDao;

    @DisplayName("Testing QuestionService")
    @Test
    void executeTest() {
        List<Question> mockQuestions = getQuestions();

        when(questionDao.findAll()).thenReturn(mockQuestions);

        List<Question> result = questionDao.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Is there life on Mars?", result.get(0).text());
        assertEquals("How should resources be loaded form jar in Java?", result.get(1).text());

        assertEquals(2, result.get(0).answers().size());
        assertEquals(2, result.get(1).answers().size());

        assertEquals("Science doesn't know this yet", result.get(0).answers().get(0).text());
        assertTrue(result.get(0).answers().get(0).isCorrect());

        verify(questionDao, times(1)).findAll();
    }

    private static List<Question> getQuestions() {
        List<Answer> answers1 = List.of(
                new Answer("Science doesn't know this yet", true),
                new Answer("Certainly. The red UFO is from Mars. And green is from Venus", false)
        );

        List<Answer> answers2 = List.of(
                new Answer("ClassLoader#geResourceAsStream or ClassPathResource#getInputStream", true),
                new Answer("ClassLoader#geResource#getFile + FileReader", false)
        );

        return List.of(
                new Question("Is there life on Mars?", answers1),
                new Question("How should resources be loaded form jar in Java?", answers2)
        );
    }

}
