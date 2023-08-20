package com.hussien.stcassessment.service;

import org.springframework.web.multipart.MultipartFile;

import com.hussien.stcassessment.dto.request.ChildItemCreationRequest;
import com.hussien.stcassessment.dto.request.ItemCreationRequest;
import com.hussien.stcassessment.dto.response.FileWithContent;
import com.hussien.stcassessment.dto.response.ItemResponse;

public interface FileService {
    ItemResponse createSpace(ItemCreationRequest dto, String userEmail);

    ItemResponse createFolder(ChildItemCreationRequest dto, String userEmail);

    ItemResponse createFile(ChildItemCreationRequest dto, MultipartFile file, String userEmail);

    ItemResponse getFileMeta(Long itemId, String userEmail);

    FileWithContent getFileBinary(Long itemId, String userEmail);
}
