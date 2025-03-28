package ru.otus.hw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw.service.RunnerService;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        var testRunnerService = context.getBean(RunnerService.class);
        testRunnerService.run();

    }
}