package vttp.batchb.ssf.practice_test.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.batchb.ssf.practice_test.models.Project;
import vttp.batchb.ssf.practice_test.services.TodoListService;
import static vttp.batchb.ssf.practice_test.utilities.TodoUtility.*;

@Controller
@RequestMapping
public class TodoListController {
    
    @Autowired
    private TodoListService todoSvc;

    @PostMapping("/listing")
    public ModelAndView postAddForm(@RequestParam MultiValueMap<String, String> form) {

        String projName = form.getFirst("projName");
        String description = form.getFirst("description");
        String priority = form.getFirst("priority");
        String status = form.getFirst("status");
        String dDate = form.getFirst("dueDate");

        Date dueDate = new Date();
        stringToDate(dueDate,dDate);

        Date currDate = new Date();

        Project project = new Project();
        project.setProjName(projName);
        project.setDescription(description);
        project.setDueDate(dueDate);
        project.setPriority(priority);
        project.setStatus(status);
        project.setCreatedDate(currDate);
        project.setUpdatedDate(currDate);
        
        //save and also gen id
        todoSvc.save(project);

        List<Project> projectList = todoSvc.getProjects();

        ModelAndView mav = new ModelAndView();
        mav.addObject("proj", project);
        mav.addObject("projList", projectList);
        mav.setViewName("listing");

        return mav;
    }
    
    @GetMapping("/add")
    public ModelAndView getAddForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        return mav;
    }

    @GetMapping(path = {"/", "/listing"})
    public ModelAndView getPriorityFilter(@RequestParam(required = false) String status) {
        
        ModelAndView mav = new ModelAndView();
        
        List<Project> filteredList = todoSvc.filterByStatus(status);
        List<Project> projectList = todoSvc.getProjects();
        //System.out.println(projectList);
        
        mav.addObject("filteredList", filteredList);
        mav.addObject("stat", status);
        mav.addObject("projList", projectList);
        mav.setViewName("listing");

        return mav;
    }

}
