package ru.yandex.practicum.catsgram.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class PostDaoImpl implements PostDao {
    private final Logger log = LoggerFactory.getLogger(PostDaoImpl.class);

    JdbcTemplate jdbcTemplate;

    public PostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Collection<Post> findAllByUser(User user) {
        String sql = "select * from cat_post where author_id = ? order by creation_date desc";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makePost(user, rs), user.getId());
    }

    private Post makePost(User user, ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String description = rs.getString("description");
        String photoUrl = rs.getString("photo_url");
        LocalDate creationDate = rs.getDate("creation_date").toLocalDate();
        return new Post(id, user, description, photoUrl, creationDate);
    }
}
