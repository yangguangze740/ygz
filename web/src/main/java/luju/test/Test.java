package luju.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/test.xml");

        JdbcTemplate oraJdbcTemplate = (JdbcTemplate) context.getBean("oraJdbcTemplate");

        System.out.println(oraJdbcTemplate);
    }
}
