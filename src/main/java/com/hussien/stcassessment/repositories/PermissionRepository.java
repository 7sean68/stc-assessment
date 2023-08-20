package com.hussien.stcassessment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussien.stcassessment.domain.Permission;
import com.hussien.stcassessment.domain.PermissionGroup;
import com.hussien.stcassessment.domain.enumerations.PermissionLevel;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findFirstByUserEmailAndPermissionLevel(String email, PermissionLevel permissionLevel);

    boolean existsByUserEmailAndGroupAndPermissionLevel(String email, PermissionGroup permissionGroup, PermissionLevel permissionLevel);

    boolean existsByUserEmailAndGroup(String email, PermissionGroup permissionGroup);
}