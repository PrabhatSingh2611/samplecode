package digital.windmill.audra.service;


import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.repository.AssetTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.service.impl.AssetTypeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeServiceTest {

    private static final Long ID = 1L;
    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String TITLE = "Laptops";
    private static final String ICON = "https://google.com/laptops";

    @Mock
    private AssetTypeRepository assetTypeRepository;


    @InjectMocks
    private AssetTypeServiceImpl assetTypeService;

    @Test
    void shouldFindAssetByUuid() {
        when(assetTypeRepository.findByUuid(any(UUID.class))).thenReturn(createAssetTypeEntity());
        var result = assetTypeService.findAssetByUuid(TEST_UUID);
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
    }


    @Test
    void shouldReturnAllAssetsType() {
        when(assetTypeRepository.findAll((Specification<AssetTypeEntity>) any(), any(PageRequest.class)))
                .thenReturn(createAssetTypeEntityList());
        var result = assetTypeService.getAssetsType();
        assertNotNull(result);
        assertEquals(2L, result.getTotalElements());
        assertEquals(TEST_UUID, result.getContent().get(0).getUuid());
        assertEquals(TITLE, result.getContent().get(0).getTitle());
        assertEquals(ICON, result.getContent().get(0).getIcon());

    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(assetTypeRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("location not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> assetTypeService.findAssetByUuid(TEST_UUID));
    }


    private Optional<AssetTypeEntity> createAssetTypeEntity() {
        return Optional.ofNullable(AssetTypeEntity.builder()
                .uuid(TEST_UUID)
                .icon(ICON)
                .id(ID)
                .title(TITLE)
                .build());
    }


    private Page<AssetTypeEntity> createAssetTypeEntityList() {
        return new PageImpl<>(createAssetsTypeEntity());
    }

    private List<AssetTypeEntity> createAssetsTypeEntity() {
        return Arrays.asList(AssetTypeEntity.builder()
                        .uuid(TEST_UUID)
                        .title(TITLE)
                        .icon(ICON)
                        .build(),
                AssetTypeEntity.builder()
                        .uuid(TEST_UUID)
                        .title(TITLE)
                        .icon(ICON)
                        .build()
        );
    }
}
