package ru.otus.spring.dao;

import org.springframework.core.io.Resource;
import ru.otus.spring.domain.Answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

public class AnswerDaoImpl implements AnswerDao {
    private static final String SEPARATOR = ",";
    private final Resource resource;
    private List<Answer> list;

    public AnswerDaoImpl(Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Answer> findAllAnswers() {
        return this.getAllAnswersFromResource(resource);
    }


    private List<Answer> getAllAnswersFromResource(Resource resource) {

        try {
            InputStream resourceAsStream = resource.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            list = reader.lines()
                    .map(line -> {
                        String[] csvRecord = line.split(SEPARATOR);
                        int id = Integer.parseInt(csvRecord[0].strip());
                        String answer = csvRecord[2].strip();
                        return new Answer(id,answer);
                    })
                    .collect(toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
