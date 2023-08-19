package com.hussien.stcassessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussien.stcassessment.domain.FileItem;

public interface FileItemRepository extends JpaRepository<FileItem, Long> {
}