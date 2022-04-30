package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    HashMap<String, User> users = new HashMap<>();

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User addUser (User user) {
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с такой почтой уже существует.");
        }else if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().isBlank()){
            throw new InvalidEmailException("Имейл указан неверное или отсутствует.");
        }else {
            users.put(user.getEmail(), user);
        }
        return user;
    }

    public User updateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank() || !users.containsKey(user.getEmail())) {
            throw new InvalidEmailException("Имейл указан неверное или отсутствует.");
        }else {
            users.put(user.getEmail(), user);
        }
        return user;
    }

    public User findUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        return users.get(email);
    }
}
