package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.IncorrectParameterException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@RestController()
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll(@RequestParam(name = "sort", defaultValue = "desc", required = false) String sort,
                              @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                              @RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {

        if (!(sort.equals("desc") || sort.equals("asc"))) {
            throw new IncorrectParameterException("sort");
        }
        if(size <= 0) {
            throw new IncorrectParameterException("size");
        }
        if (page < 0) {
            throw new IncorrectParameterException("page");
        }
        Integer from = size * page;
        return postService.findAll(size, sort, from);
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("post/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }
}