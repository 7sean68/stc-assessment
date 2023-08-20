package com.hussien.stcassessment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hussien.stcassessment.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT * FROM public.item where id = :id", nativeQuery = true)
    Optional<Item> findByIdNative(Long id);
}