package vttp.batchb.ssf.practice_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batchb.ssf.practice_test.services.TodoListService;

@SpringBootApplication
public class PracticeTestApplication implements CommandLineRunner {

	 @Autowired
    private TodoListService todoSvc;

    @Override 
    public void run(String...args) {
        todoSvc.convertToJson();
        //todoSvc.filterByPriority("low");
        
    }

	public static void main(String[] args) {
		SpringApplication.run(PracticeTestApplication.class, args);
	}

}
