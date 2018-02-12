package org.mfm.learn.springhateoas;


import java.util.Arrays;

import org.mfm.learn.springhateoas.model.Item;
import org.mfm.learn.springhateoas.model.List;
import org.mfm.learn.springhateoas.model.User;
import org.mfm.learn.springhateoas.repository.ItemRepository;
import org.mfm.learn.springhateoas.repository.ListRepository;
import org.mfm.learn.springhateoas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableHypermediaSupport(type= {HypermediaType.HAL})
public class Application {

    @Bean
    CommandLineRunner init(UserRepository userRepository, ListRepository listRepository, ItemRepository itemRepository) {
        return (evt) -> Arrays.asList("alex", "bob", "david")
                .forEach(username -> {
                    User user = userRepository.save(new User(username, "password"));
                    List list =  listRepository.save(new List("Default", user));
                    itemRepository.save(new Item("My first item.", list));
                });
    }

    @Bean
    public CurieProvider curieProvider() {
        return new DefaultCurieProvider("todo",
                new UriTemplate("http://www.mfm.org/todolist/rels/{rel}"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
