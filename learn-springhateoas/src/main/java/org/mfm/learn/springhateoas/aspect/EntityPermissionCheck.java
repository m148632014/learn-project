package org.mfm.learn.springhateoas.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.mfm.learn.springhateoas.exception.AccessDeniedException;
import org.mfm.learn.springhateoas.exception.EntityNotFoundException;
import org.mfm.learn.springhateoas.model.Item;
import org.mfm.learn.springhateoas.model.List;
import org.mfm.learn.springhateoas.repository.ItemRepository;
import org.mfm.learn.springhateoas.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EntityPermissionCheck {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Around("execution(public org.mfm.learn.springhateoas.model.List org.mfm.learn.springhateoas.service.ListService.*(..))")
    public Object checkQueryListAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Object entity = joinPoint.proceed();
        List list = (List) entity;
        this.checkListAccess(list);
        return list;
    }

    @Around("execution(public org.mfm.learn.springhateoas.model.Item org.mfm.learn.springhateoas.service.ItemService.*(..))")
    public Object checkQueryItemAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Object entity = joinPoint.proceed();
        Item item = (Item) entity;
        this.checkItemAccess(item);
        return item;
    }

    @Before("execution(public * org.mfm.learn.springhateoas.service.ListService.*(Long))")
    public void checkUpdateListAccess(JoinPoint joinPoint) {
        Long id = (Long)joinPoint.getArgs()[0];
        List list = this.listRepository.findOne(id);
        this.checkListAccess(list);
    }

    @Before("execution(public * org.mfm.learn.springhateoas.service.ItemService.*(Long))")
    public void checkUpdateItemAccess(JoinPoint joinPoint) {
        Long id = (Long)joinPoint.getArgs()[0];
        Item item = this.itemRepository.findOne(id);
        this.checkItemAccess(item);
    }

    private void checkListAccess(List list) {
        if (list == null) {
            throw new EntityNotFoundException();
        }
        else if (list.getUser() == null
                || list.getUser().getUsername() == null
                || !list.getUser().getUsername().equals(this.getCurrentUser())) {
            throw new AccessDeniedException();
        }
    }

    private void checkItemAccess(Item item) {
        if (item != null && item.getList() != null) {
            this.checkListAccess(item.getList());
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            User user = (User) authentication.getPrincipal();
            return user.getUsername();
        }
        return null;
    }
}
