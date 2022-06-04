package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDaoTest {
    Resource resource = new FileSystemResource("src/test/resources/questions_and_answers.csv");

    @Test
    void findAllQuestionsSize() throws IOException {
        QuestionDao questionDaoCsvFile = new QuestionDaoCsvFile(resource);
        assertEquals(5, questionDaoCsvFile.findAllQuestions().size());

    }
}
