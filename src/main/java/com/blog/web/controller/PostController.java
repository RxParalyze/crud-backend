package com.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.blog.persistence.model.Post;
import com.blog.persistence.service.impl.PostService;
import com.blog.web.util.RestPreconditions;
import com.google.common.base.Preconditions;

@Slf4j
@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "https://rxparalyze-crud-frontend-app.herokuapp.com")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public List<Post> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Post findById(@PathVariable("id") Integer id) {
        return RestPreconditions.checkFound(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post resource) {
        Preconditions.checkNotNull(resource);
        return service.create(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public Post update(@RequestBody Post resource) {
        Preconditions.checkNotNull(resource);
        return service.update(resource);
    }

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

}