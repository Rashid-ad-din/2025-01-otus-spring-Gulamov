package ru.otus.hw;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.hw.config.AppConfig;
import ru.otus.hw.service.RunnerService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        var testRunnerService = context.getBean(RunnerService.class);
        testRunnerService.run();

    }
}