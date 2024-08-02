package me.ftahmed.skel.service;

import me.ftahmed.skel.model.Role;

import java.util.List;

public interface RoleService {
    public void saveRole(Role role);
    public List<Object> isRolePresent(Role role);
    public List<Role> getRoles();
}
