package com.moon.likelion.crud.controller;

import com.moon.likelion.crud.CrudApplication;
import com.moon.likelion.crud.entity.PostEntity;
import com.moon.likelion.crud.entity.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CrudApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class PostControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Before
    public void setEntities() {
        createTestPost("first post", "first post content", "first writer");
        createTestPost("second post", "second post content", "second writer");
    }

    @Test
    public void givenValidId_whenReadPost_then200() throws Exception {
        // given
        Long id = createTestPost("Read Post", "Created on readPost()", "read_test");

        // when
        final ResultActions actions = mockMvc.perform(get("/post/{id}", id)).andDo(print());

        // then
        actions.andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.title", is("Read Post")),
                jsonPath("$.content", is("Created on readPost()")),
                jsonPath("$.writer", is("read_test"))
        );
    }

    private Long createTestPost(String title, String content, String writer) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(title);
        postEntity.setContent(content);
        postEntity.setWriter(writer);
        return postRepository.save(postEntity).getId();
    }
}
