package com.mfm.learn.spring.data.mongo.web;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfm.learn.spring.data.mongo.entity.Location;
import com.mfm.learn.spring.data.mongo.entity.Person;
import com.mfm.learn.spring.data.mongo.repository.PersonMongoRepository;

@RestController
@RequestMapping("/mongo")
public class PersonMongoController {

    @Autowired
    PersonMongoRepository personMongoRepository;

    @RequestMapping("/save")
    public Person save() {
        Person p = new Person("wyf", 32);
        Collection<Location> locations = new LinkedHashSet<>();
        Location loc1 = new Location("上海", "2009");
        Location loc2 = new Location("合肥", "2010");
        Location loc3 = new Location("广州", "2011");
        Location loc4 = new Location("马鞍山", "2012");
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        locations.add(loc4);
        p.setLocations(locations);

        return this.personMongoRepository.save(p);
    }

    @RequestMapping("/q1")
    public Person q1(String name) {
        return this.personMongoRepository.findByName(name);
    }

    @RequestMapping("/q2")
    public List<Person> q2(Integer age) {
        return this.personMongoRepository.withQueryFindByAge(age);
    }

    @RequestMapping("/q3")
    public List<Person> q3() {
        return this.personMongoRepository.findAll();
    }

}
