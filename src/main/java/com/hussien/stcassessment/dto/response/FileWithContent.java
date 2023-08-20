package com.hussien.stcassessment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileWithContent {
    private String fileName;
    private byte[] content;
}
