package com.hussien.stcassessment.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hussien.stcassessment.domain.File;
import com.hussien.stcassessment.domain.FileItem;
import com.hussien.stcassessment.domain.FolderItem;
import com.hussien.stcassessment.domain.PermissionGroup;
import com.hussien.stcassessment.domain.SpaceItem;
import com.hussien.stcassessment.domain.enumerations.ItemType;
import com.hussien.stcassessment.domain.enumerations.PermissionLevel;
import com.hussien.stcassessment.domain.interfaces.ParentItem;
import com.hussien.stcassessment.domain.interfaces.WithPermissionGroup;
import com.hussien.stcassessment.dto.request.ChildItemCreationRequest;
import com.hussien.stcassessment.dto.request.ItemCreationRequest;
import com.hussien.stcassessment.dto.response.FileWithContent;
import com.hussien.stcassessment.dto.response.ItemResponse;
import com.hussien.stcassessment.exceptions.AccessDeniedException;
import com.hussien.stcassessment.exceptions.BadRequestException;
import com.hussien.stcassessment.exceptions.ItemNotFoundException;
import com.hussien.stcassessment.repositories.FileItemRepository;
import com.hussien.stcassessment.repositories.FolderItemRepository;
import com.hussien.stcassessment.repositories.ItemRepository;
import com.hussien.stcassessment.repositories.PermissionRepository;
import com.hussien.stcassessment.repositories.SpaceItemRepository;
import com.hussien.stcassessment.service.FileService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final PermissionRepository permissionRepository;
    private final SpaceItemRepository spaceItemRepository;
    private final FolderItemRepository folderItemRepository;
    private final FileItemRepository fileItemRepository;
    private final ItemRepository itemRepository;

    @Override
    public ItemResponse createSpace(ItemCreationRequest dto, String userEmail) {
        PermissionGroup group = permissionRepository
                .findFirstByUserEmailAndPermissionLevel(userEmail, PermissionLevel.EDIT)
                .orElseThrow(AccessDeniedException::new).getGroup();
        SpaceItem space = new SpaceItem();
        space.setName(dto.getName());
        space.setPermissionGroup(group);
        space = spaceItemRepository.save(space);

        return ItemResponse.builder().id(space.getId()).name(space.getName()).type(ItemType.SPACE).build();
    }

    @Override
    public ItemResponse createFolder(ChildItemCreationRequest dto, String userEmail) {
        ParentItem parent = getItemForUpdate(dto.getParentId(), userEmail, ParentItem.class);

        FolderItem folder = new FolderItem();
        folder.setName(dto.getName());
        folder.setPermissionGroup(parent.getPermissionGroup());
        folder = folderItemRepository.save(folder);

        return ItemResponse.builder().id(folder.getId()).name(folder.getName()).type(ItemType.FOLDER).build();
    }

    @Override
    public ItemResponse createFile(ChildItemCreationRequest dto, MultipartFile multipartFile, String userEmail) {
        if (multipartFile.isEmpty()) {
            throw new BadRequestException("file can't be empty");
        }

        ParentItem parent = getItemForUpdate(dto.getParentId(), userEmail, ParentItem.class);

        File file = new File();
        try {
            file.setContent(multipartFile.getBytes());
        } catch (IOException e) {
            throw new BadRequestException("can't read file content");
        }

        FileItem fileItem = new FileItem();
        fileItem.setName(dto.getName());
        fileItem.setPermissionGroup(parent.getPermissionGroup());
        fileItem.setFile(file);
        fileItem = fileItemRepository.save(fileItem);

        return ItemResponse.builder().id(fileItem.getId()).name(fileItem.getName()).type(ItemType.FILE).build();
    }

    @Override
    public ItemResponse getFileMeta(Long itemId, String userEmail) {
        FileItem fileItem = getItemIfPermitted(itemId, userEmail, FileItem.class);
        return ItemResponse.builder().id(fileItem.getId()).name(fileItem.getName()).type(ItemType.FILE).build();
    }

    @Override
    public FileWithContent getFileBinary(Long itemId, String userEmail) {
        FileItem fileItem = getItemIfPermitted(itemId, userEmail, FileItem.class);
        byte[] content = fileItem.getFile().getContent();
        return new FileWithContent(fileItem.getName(), content);
    }

    private <T extends WithPermissionGroup> T getItemForUpdate(Long itemId, String userEmail, Class<T> clazz) {
        T item = getItem(itemId, clazz);

        if (!permissionRepository.existsByUserEmailAndGroupAndPermissionLevel(userEmail, item.getPermissionGroup(),
                PermissionLevel.EDIT)) {
            throw new AccessDeniedException();
        }

        return item;
    }

    private <T extends WithPermissionGroup> T getItemIfPermitted(Long itemId, String userEmail, Class<T> clazz) {
        T item = getItem(itemId, clazz);

        if (!permissionRepository.existsByUserEmailAndGroup(userEmail, item.getPermissionGroup())) {
            throw new AccessDeniedException();
        }

        return item;
    }

    private <T extends WithPermissionGroup> T getItem(Long itemId, Class<T> clazz) {
        return itemRepository.findByIdNative(itemId).filter(clazz::isInstance)
                .map(clazz::cast)
                .orElseThrow(() -> new ItemNotFoundException("parent with id " + itemId));
    }

}
