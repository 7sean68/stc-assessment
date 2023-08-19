package com.hussien.stcassessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussien.stcassessment.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}