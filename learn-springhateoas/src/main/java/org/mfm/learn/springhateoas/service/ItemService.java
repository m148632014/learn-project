package org.mfm.learn.springhateoas.service;


import java.util.Collection;

import org.mfm.learn.springhateoas.model.Item;
import org.mfm.learn.springhateoas.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Collection<Item> findByListId(Long listId) {
        return this.itemRepository.findByListId(listId);
    }

    public Item findOne(Long id) {
        return this.itemRepository.findOne(id);
    }

    public Item markAsCompleted(Long id) {
        Item item = this.itemRepository.findOne(id);
        if (item != null) {
            item.markAsCompleted();
            this.save(item);
        }
        return item;
    }

    public Item markAsUncompleted(Long id) {
        Item item = this.itemRepository.findOne(id);
        if (item != null) {
            item.markAsUncompleted();
            this.save(item);
        }
        return item;
    }

    public Item save(Item item) {
        return this.itemRepository.save(item);
    }

    public void delete(Long id) {
        this.itemRepository.delete(id);
    }
}
