package vttp.batchb.ssf.practice_test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.batchb.ssf.practice_test.services.TodoListService;

@Controller
@RequestMapping
public class TodoListController {
    
    @Autowired
    private TodoListService todoSvc;

    


}
