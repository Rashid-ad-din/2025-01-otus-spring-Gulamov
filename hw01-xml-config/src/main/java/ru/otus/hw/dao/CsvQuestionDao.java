package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final FileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {

        String fileName = fileNameProvider.getFileName();
        ClassLoader classLoader = getClass().getClassLoader();
        if (fileName == null) {
            throw new QuestionReadException("Отустствует файл с вопросами");
        }

        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             var reader = new BufferedReader(new InputStreamReader(
                     Objects.requireNonNull(inputStream),
                     StandardCharsets.UTF_8
             ))
        ) {
            CsvToBean<QuestionDto> csvToBean = getCsvToBean(reader);
            List<QuestionDto> questionDtoList = csvToBean.parse();
            return questionDtoList.stream()
                    .map(QuestionDto::toDomainObject)
                    .collect(Collectors.toList());
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