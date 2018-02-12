package org.mfm.learn.springhateoas.repository;


import java.util.Collection;

import org.mfm.learn.springhateoas.model.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ListRepository extends PagingAndSortingRepository<List, Long> {
    Collection<List> findByUserUsername(String username);
}
