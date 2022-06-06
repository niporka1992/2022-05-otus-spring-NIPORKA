package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;

@ComponentScan
@PropertySource("/application.properties")
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService service = context.getBean(QuestionService.class);

        try {
            service.run();
        } catch (IOException e) {
            LOGGER.error("File is not available");
            System.out.println("ERROR");
        }
    }
}
