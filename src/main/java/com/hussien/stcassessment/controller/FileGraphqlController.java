package com.hussien.stcassessment.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.hussien.stcassessment.dto.response.ItemResponse;
import com.hussien.stcassessment.service.FileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FileGraphqlController {
    private final FileService fileService;

    @QueryMapping
    ItemResponse getFile(@Argument Long id, @Argument String email) {
        return fileService.getFileMeta(id, email);
    }
}
