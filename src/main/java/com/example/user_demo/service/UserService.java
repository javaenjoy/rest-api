package com.example.user_demo.service;


import com.example.user_demo.domain.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private static List<User> users = new ArrayList<User>();
    private static int userCount = 3;

    static {
        users.add(new User(1, "java1", "2021-01-01"));
        users.add(new User(2, "java2", "2021-01-02"));
        users.add(new User(3, "java3", "2021-01-03"));
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(Integer id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User delete(Integer id) {
        User user = findById(id);
        if (user != null) {
            users.remove(user);
            return user;
        }
        return null;
    }

}








