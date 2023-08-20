package com.hussien.stcassessment.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hussien.stcassessment.dto.request.ChildItemCreationRequest;
import com.hussien.stcassessment.dto.response.FileWithContent;
import com.hussien.stcassessment.dto.response.ItemResponse;
import com.hussien.stcassessment.service.FileService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ItemResponse createFile(@RequestHeader("email") String userEmail,
            @RequestPart(required = true) ChildItemCreationRequest dto,
            @RequestPart MultipartFile file) {
        return fileService.createFile(dto, file, userEmail);
    }

    @GetMapping("meta/{item_id}")
    ItemResponse getFileMeta(@RequestHeader("email") String userEmail,
            @PathParam("item_id") Long itemId) {
        return fileService.getFileMeta(itemId, userEmail);
    }

    @GetMapping(value = "binary/{item_id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> getFileBinary(@RequestHeader("email") String userEmail,
            @PathParam("item_id") Long itemId) {
        FileWithContent response = fileService.getFileBinary(itemId, userEmail);
        ByteArrayResource resource = new ByteArrayResource(response.getContent());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(response.getFileName())
                                .build().toString())
                .body(resource);
    }
}
