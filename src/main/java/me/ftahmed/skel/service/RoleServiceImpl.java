package me.ftahmed.skel.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ftahmed.skel.model.Role;
import me.ftahmed.skel.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Object> isRolePresent(Role role) {
        boolean roleExists = false;
        String message = null;
        Optional<Role> existingRoleName = roleRepository.findByName(role.getName());
        if(existingRoleName.isPresent()){
            roleExists = true;
            message = "Role Already Present!";
        }
        System.out.println("existingRoleName.isPresent() - "+existingRoleName.isPresent());
        return Arrays.asList(roleExists, message);
    }
    
}
