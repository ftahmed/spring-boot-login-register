package me.ftahmed.skel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ftahmed.skel.model.Privilege;
import me.ftahmed.skel.repository.PrivilegeRepository;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    public List<Privilege> getPrivileges() {
        return privilegeRepository.findAll();
    }
    
}
