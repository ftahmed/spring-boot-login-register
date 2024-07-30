package me.ftahmed.skel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import me.ftahmed.skel.model.Role;
import me.ftahmed.skel.repository.RoleRepository;

@Component
public class RoleConverter implements Converter<String, Role> {

    private final static Logger logger = LoggerFactory.getLogger(RoleConverter.class);
    
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role convert(@NonNull String id) {
        logger.info("Converting id {} into a Role", id);
        
        Role role = roleRepository.getReferenceById(Long.valueOf(id));
        return role;
    }
}
