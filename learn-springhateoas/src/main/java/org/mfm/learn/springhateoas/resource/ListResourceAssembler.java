package org.mfm.learn.springhateoas.resource;


import org.mfm.learn.springhateoas.controller.ListRestController;
import org.mfm.learn.springhateoas.model.List;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class ListResourceAssembler extends ResourceAssemblerSupport<List, ListResource> {

    public ListResourceAssembler() {
        super(ListRestController.class, ListResource.class);
    }

    @Override
    public ListResource toResource(List list) {
        ListResource resource = this.createResourceWithId(list.getId(), list);
        return resource;
    }

    @Override
    protected ListResource instantiateResource(List entity) {
        return new ListResource(entity);
    }
}
