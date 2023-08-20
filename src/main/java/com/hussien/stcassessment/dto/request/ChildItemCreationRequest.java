package com.hussien.stcassessment.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChildItemCreationRequest extends ItemCreationRequest {
    private Long parentId;
}
