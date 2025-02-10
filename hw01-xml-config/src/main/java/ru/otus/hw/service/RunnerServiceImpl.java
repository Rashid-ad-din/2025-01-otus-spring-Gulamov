package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private final QuestionService questionService;

    @Override
    public void run() {
        questionService.executeTest();
    }
}