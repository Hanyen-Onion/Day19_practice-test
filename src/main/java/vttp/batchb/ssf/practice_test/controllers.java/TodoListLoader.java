package vttp.batchb.ssf.practice_test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vttp.batchb.ssf.practice_test.models.Project;
import vttp.batchb.ssf.practice_test.services.TodoListService;

@Component
public class TodoListLoader implements CommandLineRunner{
    
    @Autowired
    private TodoListService todoSvc;

    @Override 
    public void run(String...args) {
        //Project project = new Project();
        todoSvc.convertToJson();
        //todoSvc.save(project);;
    }


}
