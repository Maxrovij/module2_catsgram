package ru.yandex.practicum.catsgram.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate =  jdbcTemplate;
    }

    @Override
    public Optional<User> findUserByLogin(String id) {
        SqlRowSet userRowSet = jdbcTemplate.queryForRowSet("select * from cat_user where id = ?", id);
        if(userRowSet.next()) {
            User user = new User(
                    userRowSet.getString("id"),
                    userRowSet.getString("nickname"),
                    userRowSet.getString("username"));

            log.info("Найден пользователь: {} {}", userRowSet.getString("id"), userRowSet.getString("nickname"));

            return Optional.of(user);
        }else {
            log.info("Пользователь с идентификатором {} не найден!", id);
            return Optional.empty();
        }
    }
}
