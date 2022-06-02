package ru.yandex.practicum.catsgram.model;

import java.time.LocalDate;

public class Post {

    private Long id;
    private final User author; // автор
    private final LocalDate creationDate; // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    public Post(User author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
        creationDate = LocalDate.now();
    }

    public Post(Long id, User author, String description, String photoUrl, LocalDate creationDate) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}