package com.mfm.learn.spring.data.redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfm.learn.spring.data.redis.entity.Person;
import com.mfm.learn.spring.data.redis.reposotiry.PersonRedisRepository;

@RestController
@RequestMapping("/redis")
public class PersonRedisController {

    @Autowired
    PersonRedisRepository redisPersonDao;

    @RequestMapping("/set")
    //1
    public void set() {
        Person person = new Person("1", "wyf", 32);
        this.redisPersonDao.save(person);
        this.redisPersonDao.stringRedisTemplateDemo();
    }

    @RequestMapping("/getStr")
    //2
    public String getStr(String key) {
        return this.redisPersonDao.getString(key);
    }

    @RequestMapping("/getPerson")
    //3
    public Person getPerson() {
        return this.redisPersonDao.getPerson();
    }
}
