package vttp.batchb.ssf.practice_test.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        List<Project> projectList = todoSvc.getProjects();
        //System.out.println("2");

        mav.addObject(LOGIN, login);
        mav.addObject("projList",projectList);
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
    @PostMapping("/add")
    public ModelAndView postAddFormObj(@Valid @ModelAttribute Project project, BindingResult bind) {
        ModelAndView mav = new ModelAndView();

        if (bind.hasErrors()) {
            mav.setViewName("add");
        }

        mav.setViewName("listing/add");
        return mav;
    }

    @PostMapping("/lisiting/add")
    public ModelAndView postAddForm( @RequestBody MultiValueMap<String, String> form) {
        ModelAndView mav = new ModelAndView();

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

        mav.addObject("proj", project);
        mav.addObject("projList", projectList);

        return mav;
    }

    @GetMapping("/add")
    public ModelAndView getAddForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject("project", new Project());
    
        return mav;
    }

    @GetMapping(path = {"/listing", "listing/add","listing/login"})
    public ModelAndView getPriorityFilter(
        @RequestParam(required = false) String status, 
    HttpSession sess) {
        
        ModelAndView mav = new ModelAndView();
        
        List<Project> filteredList = todoSvc.filterByStatus(status);
        List<Project> projectList = todoSvc.getProjects();
        //System.out.println(projectList);

        //get session
        Login login = getLoginInfo(sess);
        //System.out.println("3");
        
        mav.addObject("filteredList", filteredList);
        mav.addObject("stat", status);
        mav.addObject("projList", projectList);
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
