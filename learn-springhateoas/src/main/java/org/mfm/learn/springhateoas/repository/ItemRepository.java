package org.mfm.learn.springhateoas.repository;


import java.util.Collection;

import org.mfm.learn.springhateoas.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    Collection<Item> findByListId(Long id);
}
