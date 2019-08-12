package com.junitests;

import com.fida.app.Application;
import com.fida.models.Log;
import com.fida.services.LogServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = Application.class)
public class DBTest {

    @Autowired
    private LogServiceImpl logService;


    @Before
    public void setUp(){

    }

    @Test
    public void testInsertToBd(){
        Log log = new Log();
        log.setSource("test");
        log.setDateTime(new Date().getTime());
        log.setMessage("APPLICATION HAS BEEN CRUSHED");

        logService.save(log);
    }

    @Test
    public void testLoadFromDbByDateTime(){
        Long start = 1564574587068L;
        Long end = 1564574770836L;

        List<Log> logs = logService.findAllByDateTime(start, end);
        for (Log log: logs) {
            System.out.println(log);
        }
    }

    @Test
    public void testLoadAllBySource(){
        String source = "home";
        Long start = 1564574587068L;
        Long end = 1564574770836L;

        List<Log> logs = logService.findAllByDateTimeAndSource(start, end, source);
        for (Log log: logs) {
            System.out.println(log);
        }
    }

    @Test
    public void testGetSources(){
        List<String> sources = logService.getSources();
        for (String source: sources) {
            System.out.println(source);
        }
    }
}
