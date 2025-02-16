package ru.otus.hw.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Data
public class AppProperties implements FileNameProvider {

    private String fileName;

    public AppProperties(@Value("${file.name}") String fileName) {
        this.fileName = fileName;
    }

}