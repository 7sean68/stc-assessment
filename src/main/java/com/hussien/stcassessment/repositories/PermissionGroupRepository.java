package com.hussien.stcassessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussien.stcassessment.domain.PermissionGroup;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
}