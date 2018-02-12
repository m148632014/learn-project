package org.mfm.learn.springhateoas.service;


import java.util.Collection;

import org.mfm.learn.springhateoas.model.List;
import org.mfm.learn.springhateoas.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListService {
    @Autowired
    private ListRepository listRepository;

    public List findOne(Long id) {
        return this.listRepository.findOne(id);
    }

    public Collection<List> findByUserUsername(String username) {
        return this.listRepository.findByUserUsername(username);
    }

    public List save(List list) {
        return this.listRepository.save(list);
    }

    public void delete(Long id) {
        this.listRepository.delete(id);
    }
}
