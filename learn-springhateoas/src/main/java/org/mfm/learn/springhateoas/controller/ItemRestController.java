package org.mfm.learn.springhateoas.controller;


import org.mfm.learn.springhateoas.command.CreateItemCommand;
import org.mfm.learn.springhateoas.model.Item;
import org.mfm.learn.springhateoas.model.List;
import org.mfm.learn.springhateoas.resource.ItemResource;
import org.mfm.learn.springhateoas.resource.ItemResourceAssembler;
import org.mfm.learn.springhateoas.service.ItemService;
import org.mfm.learn.springhateoas.service.ListService;
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
@ExposesResourceFor(Item.class)
@RequestMapping(value = "/lists/{listId}/items")
public class ItemRestController {

    @Autowired
    private ListService listService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private EntityLinks entityLinks;

    @RequestMapping(method = RequestMethod.GET)
    public Resources<ItemResource> readItems(@PathVariable Long listId) {
        Link link = ControllerLinkBuilder.linkTo(ItemRestController.class, listId).withSelfRel();
        return new Resources<ItemResource>(
                new ItemResourceAssembler().toResources(this.itemService.findByListId(listId)),
                link
        );
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ItemResource readItem(@PathVariable Long listId, @PathVariable Long itemId) {
        return new ItemResourceAssembler().toResource(this.itemService.findOne(itemId));
    }

    @RequestMapping(value = "/{itemId}/markAsCompleted", method = RequestMethod.PUT)
    public ItemResource markAsCompleted(@PathVariable Long listId, @PathVariable Long itemId) {
        return new ItemResourceAssembler().toResource(this.itemService.markAsCompleted(itemId));
    }

    @RequestMapping(value = "/{itemId}/markAsUncompleted", method = RequestMethod.PUT)
    public ItemResource markAsUncompleted(@PathVariable Long listId, @PathVariable Long itemId) {
        return new ItemResourceAssembler().toResource(this.itemService.markAsUncompleted(itemId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@PathVariable Long listId, @RequestBody CreateItemCommand createItemCommand) {
        List list = this.listService.findOne(listId);
        Item item = new Item(createItemCommand.getDescription(), createItemCommand.getPriority(), list);
        item = this.itemService.save(item);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ItemRestController.class).readItem(listId, item.getId())).toUri());
        return new ResponseEntity<Object>(responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItem(@PathVariable Long listId, @PathVariable Long itemId) {
        this.itemService.delete(itemId);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
