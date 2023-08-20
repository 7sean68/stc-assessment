package com.hussien.stcassessment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hussien.stcassessment.dto.request.ItemCreationRequest;
import com.hussien.stcassessment.dto.response.ItemResponse;
import com.hussien.stcassessment.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/space")
@RequiredArgsConstructor
public class SpaceController {
    private final FileService fileService;

    @PostMapping
    ItemResponse createSpace(@RequestHeader("email") String userEmail, @RequestBody ItemCreationRequest body) {
        return fileService.createSpace(body, userEmail);
    }
}
