package com.hussien.stcassessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussien.stcassessment.domain.SpaceItem;

public interface SpaceItemRepository extends JpaRepository<SpaceItem, Long> {
}