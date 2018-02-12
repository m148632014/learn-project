package org.mfm.learn.springhateoas.resource;



import java.time.LocalDateTime;

import org.mfm.learn.springhateoas.controller.ItemRestController;
import org.mfm.learn.springhateoas.controller.ListRestController;
import org.mfm.learn.springhateoas.model.Item;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public class ItemResource extends Resource<Item> {

    private final String description;

    private final Item.Priority priority;

    private final boolean completed;

    private final LocalDateTime completedAt;

    public ItemResource(Item item) {
        super(item);
        this.description = item.getDescription();
        this.priority = item.getPriority();
        this.completed = item.isCompleted();
        this.completedAt = item.getCompletedAt();

        Long itemId = item.getId();
        Long listId = item.getList().getId();
        if (item.isCompleted()) {
            this.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ItemRestController.class).markAsUncompleted(listId, itemId)).withRel("mark-as-uncompleted"));
        }
        else {
            this.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ItemRestController.class).markAsCompleted(listId, itemId)).withRel("mark-as-completed"));
        }
        this.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ListRestController.class).readList(listId)).withRel("collection"));
    }

    public String getDescription() {
        return this.description;
    }

    public Item.Priority getPriority() {
        return this.priority;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public LocalDateTime getCompletedAt() {
        return this.completedAt;
    }
}
