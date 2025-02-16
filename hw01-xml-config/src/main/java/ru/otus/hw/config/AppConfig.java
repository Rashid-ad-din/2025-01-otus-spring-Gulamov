package ru.otus.hw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.PrintStream;

@Configuration
@ComponentScan("ru.otus.hw")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    PrintStream printStream() {
        return System.out;
    }

}
