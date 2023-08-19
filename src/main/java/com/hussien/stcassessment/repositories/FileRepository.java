package com.hussien.stcassessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussien.stcassessment.domain.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
