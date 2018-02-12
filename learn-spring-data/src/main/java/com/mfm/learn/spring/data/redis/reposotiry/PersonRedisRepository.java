package com.mfm.learn.spring.data.redis.reposotiry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.mfm.learn.spring.data.redis.entity.Person;

@Repository
public class PersonRedisRepository {

    @Autowired
    StringRedisTemplate stringRedisTemplate; //1

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr; //3

    @Autowired
    RedisTemplate<Object, Object> redisTemplate; //2

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOps; //4

    public void stringRedisTemplateDemo() { //5
        this.valOpsStr.set("xx", "yy");
    }

    public void save(Person person) { //6
        this.valOps.set(person.getId(), person);
    }

    public String getString(String key) {//7
        return this.valOpsStr.get(key);
    }

    public Person getPerson() {//8
        return (Person) this.valOps.get("1");
    }

}
