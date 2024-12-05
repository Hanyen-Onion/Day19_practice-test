package vttp.batchb.ssf.practice_test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.batchb.ssf.practice_test.models.Project;
import vttp.batchb.ssf.practice_test.services.TodoListService;

@Controller
@RequestMapping()
public class TodoListController {
    
    @Autowired
    private TodoListService todoSvc;

    @GetMapping("/listing")
    public ModelAndView getPriorityFilter(@RequestParam(required = false) String status) {
        
        ModelAndView mav = new ModelAndView();
        
        List<Project> filteredList = todoSvc.filterByStatus(status);
        
        mav.addObject("filteredList", filteredList);
        mav.addObject("stat", status);
        mav.setViewName("listing");

        return mav;
    }

    @GetMapping()
    public ModelAndView getList() {

        ModelAndView mav = new ModelAndView();

        List<Project> projectList = todoSvc.getProjects();
        
        mav.setViewName("listing");
        mav.addObject("projList", projectList);

        return mav;
    }

}
