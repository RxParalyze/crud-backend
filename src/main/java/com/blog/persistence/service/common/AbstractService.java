package com.blog.persistence.service.common;


import java.io.Serializable;
import java.util.List;

import com.blog.persistence.IOperations;
import com.google.common.collect.Lists;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

    // read - one

    @Override
    @Transactional(readOnly = true)
    public T findById(final int id) {
        return getDao().findById(id).orElse(null);
    }

    // read - all

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

    @Override
    public Page<T> findPaginated(final int page, final int size) {
        return getDao().findAll(PageRequest.of(page, size));
    }

    // write
    @Override
    public T create(final T entity) {
        return getDao().save(entity);
    }
    @Override
    public T update(final T entity) {
        return getDao().save(entity);
    }

    @Override
    public void delete(final T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteById(final int entityId) {
        getDao().deleteById(entityId);
    }

    protected abstract PagingAndSortingRepository<T, Integer> getDao();

}