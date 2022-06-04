package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerDaoTest {
    Resource resource = new FileSystemResource("src/test/resources/questions_and_answers.csv");

    @Test
    void findAllAnswerSize() throws IOException {
        AnswerDaoCsvFile answerDaoCsvFile = new AnswerDaoCsvFile(resource);
        assertEquals(5, answerDaoCsvFile.findAllAnswers().size());

    }
}