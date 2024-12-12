package vttp.batchb.ssf.practice_test;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batchb.ssf.practice_test.services.TodoListService;
import vttp.batchb.ssf.practice_test.utilities.TodoUtility;

@SpringBootApplication
public class PracticeTestApplication implements CommandLineRunner {

	 @Autowired
    private TodoListService todoSvc;

    @Override 
    public void run(String...args){
        todoSvc.convertToJson();

    }

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(PracticeTestApplication.class, args);
	}

}
