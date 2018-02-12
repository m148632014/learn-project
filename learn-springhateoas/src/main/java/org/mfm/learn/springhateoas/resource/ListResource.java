package org.mfm.learn.springhateoas.resource;


import org.mfm.learn.springhateoas.controller.ItemRestController;
import org.mfm.learn.springhateoas.model.List;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public class ListResource extends Resource<List> {

    private final String name;

    public ListResource(List list) {
        super(list);
        this.name = list.getName();
        Long listId = list.getId();
        this.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ItemRestController.class).readItems(listId)).withRel("items"));
    }

    public String getName() {
        return this.name;
    }
}
