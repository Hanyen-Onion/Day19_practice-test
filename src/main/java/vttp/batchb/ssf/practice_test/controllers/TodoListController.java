package vttp.batchb.ssf.practice_test.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.batchb.ssf.practice_test.models.Login;
import vttp.batchb.ssf.practice_test.models.Project;
import vttp.batchb.ssf.practice_test.services.TodoListService;
import static vttp.batchb.ssf.practice_test.utilities.TodoUtility.*;

@Controller
@RequestMapping
public class TodoListController {
    
    @Autowired
    private TodoListService todoSvc;

    public static final String LOGIN = "login";
    public static final String PROJECT_LIST = "projList";
    public static final String PROJECT = "project";

    @GetMapping("/listing/delete")
    public ModelAndView getDelOrder(@RequestParam String id) {
        ModelAndView mav = new ModelAndView();
        
        todoSvc.delProject(id);
        
        return mav;
    }
    
    @GetMapping("/add/{id}")
    public ModelAndView getUpdateById( @PathVariable String id) {
        ModelAndView mav = new ModelAndView();

        Project projToUpdate = todoSvc.getProjById(id);

        System.out.println("get editing project duedate " + projToUpdate.getDueDate().toString());

        mav.addObject(PROJECT, projToUpdate);
        mav.addObject("dueDate", dateToString(projToUpdate.getDueDate()));
        mav.addObject("createdDate", dateToString(projToUpdate.getCreatedDate()));
        mav.setViewName("/add");

        return mav;
    }

    @PostMapping("listing/login")
    public ModelAndView postLogin(@ModelAttribute Login login, 
    HttpSession sess) {

        ModelAndView mav = new ModelAndView();

        System.out.println(login);

       //get session
       Login pastLogin = getLoginInfo(sess);
       if (pastLogin != login) {
            mav.setViewName("refused");
        }
        List<Project> projectList = todoSvc.getAllProjects();
        //System.out.println("2");

        mav.addObject(LOGIN, login);
        mav.addObject( PROJECT_LIST,projectList);
        mav.setViewName("listing");

        return mav;
    }

    @GetMapping(path={"/", "/login"})
    public ModelAndView getLogin(HttpSession sess) {

        ModelAndView mav = new ModelAndView();

        Login login = getLoginInfo(sess);
        //System.out.println("1");

        mav.addObject(LOGIN, login);
        mav.setViewName("login");

        return mav;
    }

    //for validation
    @PostMapping("/listing")
    public ModelAndView postAddFormObj(@Valid @ModelAttribute Project project, BindingResult bind) {
        ModelAndView mav = new ModelAndView();

        System.out.println(project.toString());

        if (bind.hasErrors()) {
            System.out.printf("%s\n", bind.hasErrors());
            mav.setViewName("add");
            return mav;
        } 
        System.out.println("to listing");
        mav.setViewName("listing");
        return mav;
    }
    //for create date to work need to implement form field for create date probably since no session 
    //add -> listing 
    @PostMapping("/add")
    public ModelAndView postAddForm( @RequestBody MultiValueMap<String, String> form, @RequestBody String CreatedDate) {
        ModelAndView mav = new ModelAndView();

        String projName = form.getFirst("projName");
        String description = form.getFirst("description");
        String priority = form.getFirst("priority");
        String status = form.getFirst("status");
        String dDate = form.getFirst("dueDate");

        Date dueDate = stringToDateHtml(dDate);

        Date currDate = new Date();

        Project project = new Project();

        project.setProjName(projName);
        project.setDescription(description);
        project.setDueDate(dueDate);
        project.setPriority(priority);
        project.setStatus(status);
        //Check for created date --> to correctly set createdDate 
        if (project.getCreatedDate() != null) {

            project.setCreatedDate(project.getCreatedDate());
            System.out.println("created date is not null");
        } else {
            System.out.println("created date is null");
            project.setCreatedDate(currDate);
        }
        project.setUpdatedDate(currDate);
        
        //save and also gen id
        todoSvc.save(project);

        List<Project> projectList = todoSvc.getAllProjects();

        mav.addObject("proj", project);
        mav.addObject( PROJECT_LIST, projectList);
        mav.setViewName("add");
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView getAddForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject(PROJECT, new Project());
    
        return mav;
    }

    @GetMapping(path = {"/listing", "listing/add","listing/login"})
    public ModelAndView getPriorityFilter(
        @RequestParam(required = false) String status, 
    HttpSession sess) {
        
        ModelAndView mav = new ModelAndView();
        
        List<Project> filteredList = todoSvc.filterByStatus(status);
        List<Project> projectList = todoSvc.getAllProjects();
        //System.out.println(projectList);

        //get session
        Login login = getLoginInfo(sess);
        //System.out.println("3");
        
        mav.addObject("filteredList", filteredList);
        mav.addObject("stat", status);
        mav.addObject( PROJECT_LIST, projectList);
        mav.addObject(LOGIN,login);
        mav.setViewName("listing");

        return mav;
    }

    @GetMapping("/refused")
    public ModelAndView getRefused() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("refused");

        return mav;
    }

}
