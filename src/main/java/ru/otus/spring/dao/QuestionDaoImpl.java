package ru.otus.spring.dao;

import lombok.Data;
import org.springframework.core.io.Resource;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Data
public class QuestionDaoImpl implements QuestionDao {

    private static final String SEPARATOR = ",";
    private final Resource resource;
    private List<Question> list;

    public QuestionDaoImpl(Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Question> findAllQuestions() {
        return this.getAllQuestionsFromResource(resource);
    }

    private List<Question> getAllQuestionsFromResource(Resource resource) {

        try {
            InputStream resourceAsStream = resource.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            list = reader.lines()
                    .map(line -> {
                        String[] csvRecord = line.split(SEPARATOR);
                        int id = Integer.parseInt(csvRecord[0].strip());
                        String question = csvRecord[1].strip();

                        return new Question(id, question);
                    })
                    .collect(toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
