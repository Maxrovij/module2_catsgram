package ru.yandex.practicum.catsgram.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class FollowDaoImpl implements FollowDao {
    Logger log = LoggerFactory.getLogger(FollowDaoImpl.class);

    JdbcTemplate jdbcTemplate;
    UserDao userDao;
    PostDao postDao;

    public FollowDaoImpl(JdbcTemplate jdbcTemplate, UserDao userDao, PostDao postDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @Override
    public List<Post> getFollowFeed(String userId, int max) {
        String sql = "select cat_user from cat_follow where cat_follower = ?";
        List<String> follows = jdbcTemplate.query(sql, (rs, rowNum) ->rs.getString("cat_user"), userId);

        List<User> authors = follows.stream()
                .map(userDao::findUserByLogin)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if (authors.isEmpty()) return Collections.emptyList();

        return authors.stream()
                .map(postDao::findAllByUser)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .limit(max)
                .collect(Collectors.toList());
    }

}
