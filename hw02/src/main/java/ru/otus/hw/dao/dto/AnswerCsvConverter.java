package ru.otus.hw.dao.dto;

import com.opencsv.bean.AbstractCsvConverter;
import org.springframework.stereotype.Component;
import ru.otus.hw.domain.Answer;

@Component
public class AnswerCsvConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        String[] valueArr = value.split("%");
        if (valueArr.length < 2) {
            throw new IllegalArgumentException("Некорректный формат ответа: " + value);
        }

        return new Answer(valueArr[0].trim(), Boolean.parseBoolean(valueArr[1].trim()));
    }
}