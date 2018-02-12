package org.mfm.learn.springhateoas.command;


import org.mfm.learn.springhateoas.model.Item;

public class CreateItemCommand {
    private String description;
    private Item.Priority priority;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item.Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Item.Priority priority) {
        this.priority = priority;
    }
}
