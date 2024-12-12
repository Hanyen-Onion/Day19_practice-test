package vttp.batchb.ssf.practice_test.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.batchb.ssf.practice_test.models.Project;
import static vttp.batchb.ssf.practice_test.utilities.TodoUtility.*;

@Repository
public class TodoListRepository {
    
    @Autowired @Qualifier("redis-object")
    private RedisTemplate<String, Object> template;

    //del key(id)
    public void deleteProjectById(String id) {
        template.delete(id); 
    }

    //hgetall key(id)
    public Optional<Project> getProjectInfoById(String id) {
        HashOperations<String, String, Object> hashOps = template.opsForHash();
        Map<String, Object> project = hashOps.entries(id);
        
        //if the id does not exist or has no project info stored
        if (project.isEmpty()) {
            return Optional.empty();
        }

        //take project info out from redis and
        //make them into an Project object again
        Project result = new Project();

        result.setProjName(project.get("projName").toString());
        result.setDescription(project.get("description").toString());
        result.setDueDate(ephocToDate(project.get("dueDate")));
        result.setPriority(project.get("priority").toString());
        result.setStatus(project.get("status").toString());
        result.setCreatedDate(ephocToDate(project.get("createdDate")));
        result.setUpdatedDate(ephocToDate(project.get("updatedDate")));
        result.setId(id);

        return Optional.of(result);
    }

    // keys *
    public Set<String> getProjectIds() {
        return template.keys("*");
    }

    //hset key(id) hashkey(id) value(idvalue)...
    public void saveToRedis(Project project) {
        HashOperations<String, String, Object> hashOps = template.opsForHash();
    
        // also set date to long before storing
        Map<String, Object> values = new HashMap<>();
        
        //values.put("id", project.getId());
        values.put("projName", project.getProjName());
        values.put("description", project.getDescription());
        values.put("dueDate", dateToEphoch(project.getDueDate()));
        values.put("priority", project.getPriority());
        values.put("status", project.getStatus());
        values.put("createdDate", dateToEphoch(project.getCreatedDate()));
        values.put("updatedDate", dateToEphoch(project.getUpdatedDate()));
        hashOps.putAll(project.getId(), values);
    }

}
