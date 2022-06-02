package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PostFeedController {

    private final PostService postService;

    public PostFeedController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/feed")
    public Collection<Post> findPostByUser(@RequestParam String userId,
                                           @RequestParam Integer size,
                                           @RequestParam String sort) {
        return postService.findAllByUser(userId).stream()
                .sorted((p1, p2) -> {
                    int comp = p1.getCreationDate().compareTo(p2.getCreationDate());
                    if (sort.equals("desc")) {
                        comp = -1 * comp;
                    }
                    return comp;
                })
                .limit(size)
                .collect(Collectors.toList());
    }

    @GetMapping("/feed/following")
    public List<Post> getFollowingFeed(@RequestParam String userId, @RequestParam int max) {
        return postService.getFollowingFeed(userId, max);
    }
}
