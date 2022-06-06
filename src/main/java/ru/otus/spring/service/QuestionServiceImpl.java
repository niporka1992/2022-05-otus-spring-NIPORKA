package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AnswerDao;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.service.ReaderWriter.ReaderWriterImpl;

import java.io.IOException;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    private final ReaderWriterImpl readerWriter = new ReaderWriterImpl();
    private int countRightAnswer = 0;
    private String name;


    public QuestionServiceImpl(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    public void run() throws IOException {
        name = askName();
        List<Answer> answerList = answerDao.findAllAnswers();

        questionDao.findAllQuestions().forEach(question -> {
                    readerWriter.writeToConsole(question.getName());
                    Answer answer = new Answer(readerWriter.read());
                    if (answerList.contains(answer)) {
                        answerList.remove(answer);
                        countRightAnswer++;
                    }
                }
        );

        showResult();
    }

    @Override
    public String askName() {
        System.out.println("Напишите свое имя");
        return readerWriter.read();
    }

    @Override
    public void showResult() throws IOException {
        System.out.println(name + ", вы ответили правильно на - " + countRightAnswer + " из " + questionDao.findAllQuestions().size() + " вопросов.");
    }
}
