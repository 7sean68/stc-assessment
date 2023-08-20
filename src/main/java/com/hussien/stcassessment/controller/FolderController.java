package com.hussien.stcassessment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hussien.stcassessment.dto.request.ChildItemCreationRequest;
import com.hussien.stcassessment.dto.response.ItemResponse;
import com.hussien.stcassessment.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/folder")
@RequiredArgsConstructor
public class FolderController {
    private final FileService fileService;

    @PostMapping
    ItemResponse createFolder(@RequestHeader("email") String userEmail, @RequestBody ChildItemCreationRequest body) {
        return fileService.createFolder(body, userEmail);
    }
}
