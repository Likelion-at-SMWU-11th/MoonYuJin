package com.moon.likelion.crud.controller;

import com.moon.likelion.crud.dto.PostDto;
import com.moon.likelion.crud.exception.PostNotExistException;
import com.moon.likelion.crud.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostRestController {
    private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);
    private final PostService postService;

    public PostRestController(@Autowired PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostDto postDto) {
        logger.info(postDto.toString());
        this.postService.createPost(postDto);
    }

    @GetMapping()
    public List<PostDto> readPostAll() {
        logger.info("in read post all");
        return this.postService.readPostAll();
    }

    @GetMapping("{id}")
    public PostDto readPost(@PathVariable("id") int id) {
        logger.info("in read post");
        return this.postService.readPost(id);
    }

    @GetMapping("/test-exception")
    public void throwException() {
        System.out.println("test-exception");
        throw new PostNotExistException();
    }
}