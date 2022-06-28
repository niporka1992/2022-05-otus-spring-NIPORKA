package ru.otus.spring.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.otus.spring.dao.AnswerDao;
import ru.otus.spring.dao.AnswerDaoCsvFile;
import ru.otus.spring.dao.QuestionDaoCsvFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionServiceTest {

    Resource resource = new FileSystemResource("src/test/resources/questions_and_answers_RU.csv");
    AnswerDao answerDao = new AnswerDaoCsvFile(resource);
    QuestionDaoCsvFile questionDaoCsvFile = new QuestionDaoCsvFile(resource);


    @ParameterizedTest
    @CsvFileSource(resources = "/questions_and_answers_RU.csv")
    void runGetQuestions(Integer id, String question) throws IOException {
        assertEquals(questionDaoCsvFile.findAllQuestions().get(--id).getName(), question);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/questions_and_answers_RU.csv")
    void runGetAnswers(Integer id, String question, String answer) throws IOException {
        assertEquals(answerDao.findAllAnswers().get(--id).getName(), answer);
    }
}