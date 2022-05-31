package ru.otus.spring.service;

import ru.otus.spring.dao.AnswerDao;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.service.ReaderWriter.ReaderWriterImpl;

import java.util.List;


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

    public void run() {
        name = askName();
        List<Answer> answerList = answerDao.findAllAnswers();

        questionDao.findAllQuestions().forEach(question -> {
                    Answer answer = new Answer();
                    readerWriter.writeToConsole(question.getName());
                    answer.setName(readerWriter.read());
                    if (answerList.contains(answer)) {
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
    public void showResult() {
        System.out.println(name + ", вы ответили правильно на - " + countRightAnswer + " из " + questionDao.findAllQuestions().size() + " вопросов.");
    }
}
