package ru.otus.spring.service;

import java.io.IOException;

public interface QuestionService {
    void run() throws IOException;

    String askName();

    void showResult() throws IOException;

}
