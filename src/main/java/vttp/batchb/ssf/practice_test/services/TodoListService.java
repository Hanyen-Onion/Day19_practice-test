package vttp.batchb.ssf.practice_test.services;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batchb.ssf.practice_test.models.Project;

import vttp.batchb.ssf.practice_test.repositories.TodoListRepository;
import static vttp.batchb.ssf.practice_test.utilities.TodoUtility.*;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoRepo;

    private static final String TXT = "src/main/resources/static/txt/todos.txt";
    
    public List<Project> convertToJson() {
        
        String jsonString = readTxt();
        List<Project> taskList = new LinkedList<>();

        try {
            InputStream is = new ByteArrayInputStream(jsonString.getBytes());
            JsonReader reader = Json.createReader(is);
            JsonArray dataArray = reader.readArray();

            for (int i = 0; i< dataArray.size(); i++) {
                JsonObject task = dataArray.getJsonObject(i);
                //System.out.println("print each task:" + task.toString());

                String id = task.getString("id");
                String name = task.getString("name");
                String description = task.getString("description");
                String dueDate = task.getString("due_date");
                String priority = task.getString("priority_level");
                String status = task.getString("status");
                String createDate = task.getString("created_at");
                String updatedDate = task.getString("updated_at");

                Project project = new Project();

                //convert string to date
                project.setDueDate(stringToDate(project.getDueDate(), dueDate));
                project.setCreatedDate(stringToDate(project.getCreatedDate(), createDate));
                project.setUpdatedDate(stringToDate(project.getUpdatedDate(), updatedDate));

                project.setId(id);
                project.setProjName(name);
                project.setDescription(description);
                project.setPriority(priority);
                project.setStatus(status);

                taskList.add(project);
                todoRepo.saveToRedis(project);
            } 
            System.out.println(taskList);  
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return taskList;
    }
    
    public String readTxt() {
        
        File file = new File(TXT);
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

            while( (line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("no file bruh");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("nothing to read apparently");
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
}
