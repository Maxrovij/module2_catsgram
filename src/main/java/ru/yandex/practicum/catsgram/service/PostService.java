package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    UserService userService;

    private Integer latestId = 1;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        if(userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException(String.format("Пользователь %s не найден.", post.getAuthor()));
        } else {
            post.setId(getNextId());
            posts.add(post);
        }
        return post;
    }

    private Integer getNextId() {
        return latestId++;
    }

    public Post getPostById(Integer id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(String.format("Post %s not found.", id)));
    }
}
