package vttp.batchb.ssf.practice_test.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.batchb.ssf.practice_test.models.Project;
import vttp.batchb.ssf.practice_test.services.TodoListService;

@Controller
@RequestMapping()
public class TodoListController {
    
    @Autowired
    private TodoListService todoSvc;

    @GetMapping()
    public ModelAndView getList() {
        ModelAndView mav = new ModelAndView();

        Set<String> ids = todoSvc.getIds();
        List<Project> projectList = new LinkedList<>();

        //System.out.println(ids.toString());

        ids.forEach(id -> {
            Optional<Project> opt = todoSvc.getProjectById(id);
            
            if (opt.isEmpty()) {
                mav.setViewName("not-found");
                mav.setStatus(HttpStatusCode.valueOf(404));
                System.out.println("not-found");
            }

            Project proj = opt.get();
            projectList.add(proj);  
        });

        mav.setViewName("listing");
        mav.addObject("projList", projectList);

        //System.out.println(projectList);

        return mav;
    }


}
