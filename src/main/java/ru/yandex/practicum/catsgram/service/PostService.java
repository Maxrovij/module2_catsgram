package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Collection;
import java.util.List;

@Service
public class PostService {
    private final PostDao postDao;
    private final UserService userService;
    private final FollowDao followDao;

    private Long latestId = 0L;

    @Autowired
    public PostService(PostDao postDao, UserService userService, FollowDao followDao) {
        this.postDao = postDao;
        this.followDao = followDao;
        this.userService = userService;
    }

    public Collection<Post> findAllByUser(String userId) {
        User user = userService.findUserByLogin(userId).orElseThrow(
                () -> new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден!")
        );
        return postDao.findAllByUser(user);
    }

    public List<Post> getFollowingFeed(String id, int max) {
        return followDao.getFollowFeed(id,max);
    }
}
