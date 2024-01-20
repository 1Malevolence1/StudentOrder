package dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

class DictionaryDaoImplTest {



    @BeforeAll
    public static void startUp() throws URISyntaxException, IOException, SQLException {
       URL url = DictionaryDaoImplTest.class.getClassLoader()
               .getResource("student_project.sql");

      List<String> str =  Files.readAllLines(Paths.get(url.toURI()));
      String sql = str.stream().collect(Collectors.joining());


        try (Connection con = ConnectionBuilder.getConnection();
             Statement statement = con.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    @BeforeEach
    public void  startTest(){
        System.out.println("Start Test");
    }


    @Test
    public void test1(){
        System.out.println("TEST1");
    }
    @Test
    public void test2(){
        System.out.println("TEST2");
    }


    @Test
    public void test3(){
        System.out.println("TEST3");
    }

}