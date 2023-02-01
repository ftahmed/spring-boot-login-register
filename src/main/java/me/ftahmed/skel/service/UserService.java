package me.ftahmed.skel.service;

import me.ftahmed.skel.model.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public List<Object> isUserPresent(User user);
}
