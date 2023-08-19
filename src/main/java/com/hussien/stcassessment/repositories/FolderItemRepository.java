package com.hussien.stcassessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussien.stcassessment.domain.FolderItem;

public interface FolderItemRepository extends JpaRepository<FolderItem, Long> {
}