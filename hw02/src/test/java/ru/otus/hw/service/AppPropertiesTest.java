package ru.otus.hw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Testing AppProperties")
@PropertySource("classpath:application-test.properties")
@SpringBootTest
public class AppPropertiesTest {

    private final String fileName;

    public AppPropertiesTest(@Value("${file.name}") String fileName) {
        this.fileName = fileName;
    }

    @Test
    void getFileName() {
        assertThat(fileName).isEqualTo("questions.csv");
    }

}
