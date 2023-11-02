package com.moon.likelion.crud.repo;

import com.moon.likelion.crud.dto.PostDto;
import com.moon.likelion.crud.entity.PostRepository;
import com.moon.likelion.crud.entity.PostEntity;
import com.moon.likelion.crud.controller.PostRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);
    private final PostRepository postRepository;

    public PostDao(@Autowired PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(PostDto dto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());
        postEntity.setWriter(dto.getWriter());
        postEntity.setBoardEntity(null);

        this.postRepository.save(postEntity);
    }

    public PostEntity readPost(int id) {
        Optional<PostEntity> postEntity = this.postRepository.findById((long) id);
        if (postEntity.isPresent()) {
            return postEntity.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public Iterator<PostEntity> readPostAll() {
        return this.postRepository.findAll().iterator();
    }

    public void updatePost(int id, PostDto dto) {
        Optional<PostEntity> targetEntity = this.postRepository.findById(Long.valueOf(id));
        if(!targetEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        PostEntity postEntity = targetEntity.get();

        postEntity.setTitle(
                dto.getTitle() == null ? postEntity.getTitle() : dto.getTitle()
        );

        postEntity.setContent(
                dto.getContent() == null ? postEntity.getContent() : dto.getContent()
        );

        postEntity.setWriter(
                dto.getWriter() == null ? postEntity.getWriter() : dto.getWriter()
        );

        this.postRepository.save(postEntity);
    }

    public void deletePost(int id) {
        Optional<PostEntity> targetEntity = this.postRepository.findById((long) id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.postRepository.delete(targetEntity.get());
    }
}
