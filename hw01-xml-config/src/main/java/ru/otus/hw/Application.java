package ru.otus.hw;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import ru.otus.hw.service.RunnerService;

@Configuration
@PropertySource("classpath:application.properties")
@SpringBootApplication(scanBasePackages = "ru.otus.hw")
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        var testRunnerService = context.getBean(RunnerService.class);
        testRunnerService.run();

    }
}