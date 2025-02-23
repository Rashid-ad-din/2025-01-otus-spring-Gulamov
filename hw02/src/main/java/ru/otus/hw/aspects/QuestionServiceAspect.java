package ru.otus.hw.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.otus.hw.service.IOService;

@Aspect
@Component
public class QuestionServiceAspect {

    private final IOService ioService;

    public QuestionServiceAspect(IOService ioService) {
        this.ioService = ioService;
    }

    @Around("execution(public void ru.otus.hw.service.QuestionService.execute()) ")
    public Object logExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        ioService.printLine("Test start.\n");
        long startTime = System.currentTimeMillis();
        var result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        ioService.printFormattedLine("\nTest end. Time spent: %d ms", elapsedTime);
        return result;
    }

}
