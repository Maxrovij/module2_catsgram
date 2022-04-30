package ru.yandex.practicum.catsgram.model;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private String email;
    private String nickname;
    private LocalDate birthdate;

    public User(String email, String nickname, String birthdate) {
        this.email = email;
        this.nickname = nickname;
        this.birthdate = LocalDate.parse(birthdate);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        User objUser = (User) obj;
        return Objects.equals(this.email, objUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
