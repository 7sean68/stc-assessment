package com.hussien.stcassessment;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.hussien.stcassessment.domain.FolderItem;
import com.hussien.stcassessment.exceptions.ItemNotFoundException;
import com.hussien.stcassessment.repositories.ItemRepository;
import com.hussien.stcassessment.service.FileService;
import com.hussien.stcassessment.service.impl.FileServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class FileServiceTest {
	@Mock
	ItemRepository itemRepository;

	FileService fileService;

	@BeforeEach
	void reinit() {
		fileService = new FileServiceImpl(null, null, null, null, itemRepository);
	}

	@Test
	void throwsNotFoundIfDifferentType() {
		Mockito.when(itemRepository.findByIdNative(1L)).thenReturn(Optional.of(new FolderItem()));
		assertThrows(ItemNotFoundException.class, () -> fileService.getFileMeta(1L, "some@email.com"));
		Mockito.verifyNoMoreInteractions(itemRepository);
	}

}
