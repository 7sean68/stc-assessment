package com.hussien.stcassessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussien.stcassessment.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}