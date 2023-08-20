package com.hussien.stcassessment.dto.response;

import com.hussien.stcassessment.domain.enumerations.ItemType;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ItemResponse {
    private Long id;
    private String name;
    private ItemType type;
}
