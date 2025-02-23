package ru.otus.hw.service;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;
import ru.otus.hw.config.FileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvQuestionDaoTest {

    @Test
    void execute() {

        FileNameProvider fileNameProvider = () -> "questions.csv";
        String fileName = fileNameProvider.getFileName();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             var reader = new BufferedReader(new InputStreamReader(
                     Objects.requireNonNull(inputStream),
                     StandardCharsets.UTF_8
             ))
        ) {
            CsvToBean<QuestionDto> csvToBean = getCsvToBean(reader);
            List<QuestionDto> questionDtoList = csvToBean.parse();
            List<Question> questionList = questionDtoList.stream()
                    .map(QuestionDto::toDomainObject)
                    .toList();

            assertThat(questionList.get(0).text()).isEqualTo("Is there life on Mars?");
            assertThat(questionList.get(0).answers().get(0).text()).isEqualTo("Science doesn't know this yet");
            assertThat(questionList.get(0).answers().get(0).isCorrect()).isTrue();

        } catch (Exception e) {
            throw new QuestionReadException("Ошибка при чтении CSV: " + e.getMessage(), e);
        }
    }

    private CsvToBean<QuestionDto> getCsvToBean(BufferedReader reader) {
        return new CsvToBeanBuilder<QuestionDto>(reader)
                .withType(QuestionDto.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1)
                .withSeparator(';')
                .build();
    }

}
