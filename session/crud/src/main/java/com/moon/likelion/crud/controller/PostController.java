package com.moon.likelion.crud.controller;

import com.google.gson.Gson;
import com.moon.likelion.crud.ValidTestDto;
import com.moon.likelion.crud.aspect.LogArguments;
import com.moon.likelion.crud.aspect.LogExecutionTime;
import com.moon.likelion.crud.aspect.LogReturn;
import com.moon.likelion.crud.dto.PostDto;
import com.moon.likelion.crud.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;
    // private final Gson gson;

    public PostController(
            @Autowired PostService postService,
            @Autowired Gson gson
            // @Autowired GsonComponent gson
    ) {
        this.postService = postService;
        logger.info(gson.toString());
        // this.gson = gson.getGson();
    }

    @LogArguments
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@Valid @RequestBody PostDto postDto) {
        this.postService.createPost(postDto);
        // return postDto.getTitle();
    }

    @LogReturn
    @GetMapping("/{id}")
    public PostDto readPost(@PathVariable("id") int id) {
        PostDto postDto = postService.readPost(id);
        System.out.println(postDto.getTitle());
        return postDto;
    }

    @LogExecutionTime

    @GetMapping("")
    public List<PostDto> readPostAll() {
        return this.postService.readPostAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(@PathVariable("id") int id, @RequestBody PostDto postDto) {
        this.postService.updatePost(id, postDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(@PathVariable("id") int id) {
        this.postService.deletePost(id);
    }

    @RequestMapping("/")
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "index";
    }

    @PostMapping("test-valid")
    public void testValue(@Valid @RequestBody ValidTestDto dto) {
        logger.info(dto.toString());
    }
}
