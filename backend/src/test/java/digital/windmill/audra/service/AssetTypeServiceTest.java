package digital.windmill.audra.service;


import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.repository.AssetTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.assetType.AssetTypesInput;
import digital.windmill.audra.service.impl.AssetTypeService;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetTypeServiceTest {

    private static final UUID ASSETTYPE_UUID = UUID.randomUUID();

    @Mock
    private AssetTypeRepository assetTypeRepository;


    @InjectMocks
    private AssetTypeService assetTypeService;

    @Test
    void shouldReturnAssetTypeByUuid(@Mock AssetTypeEntity assetTypeEntity) {
        when(assetTypeRepository.findByUuid(ASSETTYPE_UUID))
                .thenReturn(Optional.ofNullable(assetTypeEntity));

        var result = assetTypeService.findAssetByUuid(ASSETTYPE_UUID);
        assertNotNull(result);
        assertEquals(assetTypeEntity, result);
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(assetTypeRepository.findByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("Asset Type not found " + ASSETTYPE_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> assetTypeService.findAssetByUuid(ASSETTYPE_UUID));
    }

    @Test
    void shouldReturnAllAssetTypes(@Mock AssetTypeEntity assetTypeEntity,
                                   @Mock AssetTypesInput input) {
        var assetTypePage = createOneItemPage(assetTypeEntity);
        when(assetTypeRepository.findAll((Specification<AssetTypeEntity>) any(), any(PageRequest.class)))
                .thenReturn(assetTypePage);
        var result = assetTypeService.getAssetTypes(input);

        assertNotNull(result);
        assertSame(assetTypePage, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
    @Test
    void shouldSaveAssetType(@Mock AssetTypeEntity assetTypeEntity) {
        when(assetTypeRepository.save(assetTypeEntity)).thenReturn(assetTypeEntity);

        var result = assetTypeService.save(assetTypeEntity);
        assertNotNull(result);
        assertSame(assetTypeEntity, result);
    }
}
