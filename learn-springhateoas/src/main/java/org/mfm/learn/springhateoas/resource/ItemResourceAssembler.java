package org.mfm.learn.springhateoas.resource;


import org.mfm.learn.springhateoas.controller.ItemRestController;
import org.mfm.learn.springhateoas.model.Item;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class ItemResourceAssembler extends ResourceAssemblerSupport<Item, ItemResource> {

    public ItemResourceAssembler() {
        super(ItemRestController.class, ItemResource.class);
    }

    @Override
    public ItemResource toResource(Item item) {
        return this.createResourceWithId(item.getId(), item, item.getList().getId());
    }

    @Override
    protected ItemResource instantiateResource(Item entity) {
        return new ItemResource(entity);
    }
}
