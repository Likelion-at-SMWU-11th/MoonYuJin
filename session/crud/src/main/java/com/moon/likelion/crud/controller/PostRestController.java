//package com.moon.likelion.crud.controller;
//
//import com.moon.likelion.crud.dto.PostDto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("post")
//public class PostRestController {
//    private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);
//    private final List<PostDto> postList;
//
//    public PostRestController() {
//        this.postList = new ArrayList<>();
//    }
//
//    @PostMapping()
//    public void createPost(@RequestBody PostDto postDto) {
//        logger.info(postDto.toString());
//        this.postList.add(postDto);
//    }
//
//    @GetMapping()
//    public List<PostDto> readPostAll() {
//        logger.info("in read post all");
//        return this.postList;
//    }
//
//    @GetMapping("{id}")
//    public PostDto readPost(@PathVariable("id") int id) {
//        logger.info("in read post");
//        return this.postList.get(id);
//    }
//}