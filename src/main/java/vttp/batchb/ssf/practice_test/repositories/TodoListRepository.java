package vttp.batchb.ssf.practice_test.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.batchb.ssf.practice_test.models.Project;

@Repository
public class TodoListRepository {
    
    @Autowired @Qualifier("redis-object")
    private RedisTemplate<String, Object> template;

    //hgetall

    //keys *
    

    //hset key(id) hashkey(id) value(idvalue)...
    public void saveToRedis(Project project) {
        HashOperations<String, String, Object> hashOps = template.opsForHash();

        Map<String, Object> values = new HashMap<>();
        values.put("id", project.getId());
        values.put("task", project.getProjName());
        values.put("description", project.getDescription());
        values.put("dueDate", project.getDueDate());
        values.put("priority", project.getPriority());
        values.put("status", project.getStatus());
        values.put("createdDate", project.getCreatedDate());
        values.put("updatedDate", project.getUpdateDate());
        hashOps.putAll(project.getId(), values);
    }
}
