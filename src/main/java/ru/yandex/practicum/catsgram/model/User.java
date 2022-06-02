package ru.yandex.practicum.catsgram.model;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private String id;
    private String nickname;
    private String username;

    public User() {
    }

    public User(String id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        User objUser = (User) obj;
        return Objects.equals(this.id, objUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
