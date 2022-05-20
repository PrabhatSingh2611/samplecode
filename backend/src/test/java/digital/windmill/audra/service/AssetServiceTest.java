package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.repository.AssetRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.service.impl.AssetServiceImpl;
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

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;


    @InjectMocks
    private AssetServiceImpl service;

    private static final UUID ASSET_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String ASSET_TITLE = "Asset title";
    private static final String ASSET_SERIAL_NUMBER = "40aab8f6";
    private final static Instant LOCAL_DATE = Instant.now();


    @Test
    void shouldReturnAssetByUuid(@Mock AssetEntity assetEntity) {
        when(assetRepository.findByUuid(any(UUID.class))).thenReturn(Optional.of(assetEntity));
        var result = service.findAssetByUuid(ASSET_UUID);

        assertNotNull(result);
        assertSame(assetEntity, result);
    }

    @Test
    void shouldCreateOrUpdateAsset(@Mock AssetEntity entity) {
        when(assetRepository.save(any(AssetEntity.class))).thenReturn(entity);
        var result = service.createOrUpdateAsset(entity);
        assertNotNull(result);
        assertSame(result, entity);
    }

    @Test
    void shouldFindAll(@Mock AssetsInput assetsInput, @Mock AssetEntity assetEntity) {
        Page<AssetEntity> assetEntityPage = createOneItemPage(assetEntity);
        when(assetRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(assetEntityPage);
        var result = service.findAll(assetsInput);
        assertNotNull(result);
        assertSame(result, assetEntityPage);
    }


    @Test
    void shouldThrowDataNotFoundWhenAssetInputIsNull() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findAssetByUuid(ASSET_UUID));
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
