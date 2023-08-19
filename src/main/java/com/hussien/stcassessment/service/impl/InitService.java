package com.hussien.stcassessment.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hussien.stcassessment.domain.Permission;
import com.hussien.stcassessment.domain.PermissionGroup;
import com.hussien.stcassessment.domain.enumerations.PermissionLevel;
import com.hussien.stcassessment.repositories.PermissionGroupRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitService {
    private final PermissionGroupRepository permissionGroupRepository;

    @PostConstruct
    @Transactional
    public void initDB() {
        List<PermissionGroup> groups = permissionGroupRepository.findAll();

        if(groups.isEmpty()) {
            PermissionGroup group = new PermissionGroup();
            group.setGroupName("first-group");

            Permission permission = new Permission();
            permission.setUserEmail("admin@stc.com");
            permission.setPermissionLevel(PermissionLevel.EDIT);
            group.addPermission(permission);

            permission = new Permission();
            permission.setUserEmail("user@stc.com");
            permission.setPermissionLevel(PermissionLevel.VIEW);
            group.addPermission(permission);

            permissionGroupRepository.save(group);
        }
    }
}
