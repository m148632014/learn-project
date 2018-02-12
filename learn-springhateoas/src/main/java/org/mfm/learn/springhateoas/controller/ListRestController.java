package org.mfm.learn.springhateoas.controller;


import java.security.Principal;

import org.mfm.learn.springhateoas.command.CreateListCommand;
import org.mfm.learn.springhateoas.command.UpdateListCommand;
import org.mfm.learn.springhateoas.exception.AccessDeniedException;
import org.mfm.learn.springhateoas.model.List;
import org.mfm.learn.springhateoas.model.User;
import org.mfm.learn.springhateoas.resource.ListResource;
import org.mfm.learn.springhateoas.resource.ListResourceAssembler;
import org.mfm.learn.springhateoas.service.ListService;
import org.mfm.learn.springhateoas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(List.class)
@RequestMapping("/lists")
public class ListRestController {

    @Autowired
    private ListService listService;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityLinks entityLinks;

    @RequestMapping(method = RequestMethod.GET)
    public Resources<ListResource> readLists(Principal principal) {
        String username = principal.getName();
        Link link = ControllerLinkBuilder.linkTo(ListRestController.class).withSelfRel();
        return new Resources<ListResource>(new ListResourceAssembler().toResources(this.listService.findByUserUsername(username)), link);
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.GET)
    public ListResource readList(@PathVariable Long listId) {
        return new ListResourceAssembler().toResource(this.listService.findOne(listId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createList(Principal principal, @RequestBody CreateListCommand createListCommand) {
        User user = this.userService.findByUsername(principal.getName()).orElseThrow(AccessDeniedException::new);
        List list = new List(createListCommand.getName(), user);
        list = this.listService.save(list);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(this.entityLinks.linkForSingleResource(List.class, list).toUri());
        return new ResponseEntity<Object>(responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.PUT)
    public ListResource updateList(@PathVariable Long listId, @RequestBody UpdateListCommand updateListCommand) {
        List list = this.listService.findOne(listId);
        list.setName(updateListCommand.getName());
        list = this.listService.save(list);
        return new ListResourceAssembler().toResource(list);
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteList(@PathVariable Long listId) {
        this.listService.delete(listId);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
