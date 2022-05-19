package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.repository.AssetRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.service.impl.AssetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;


    @InjectMocks
    private AssetServiceImpl service;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String ASSET_TITLE = "Asset title";
    private static final String ASSET_SERIAL_NUMBER = "40aab8f6";
    private final static Instant LOCAL_DATE = Instant.now();


    //TODO: Rest of UT for AssetService class
    @Test
    void shouldReturnAssetByUuid() {
        when(assetRepository.findByUuid(any(UUID.class))).thenReturn(Optional.of(createAssetEntity()));
        var result = service.findAssetByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldCreateOrUpdateAsset(@Mock AssetEntity entity) {
        when(assetRepository.save(any(AssetEntity.class))).thenReturn(createAssetEntity());
        var result = service.save(entity);
        assertNotNull(result);
    }

    @Test
    void shouldThrowDataNotFoundWhenAssetInputIsNull() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findAssetByUuid(TEST_UUID));
    }


    private AssetEntity createAssetEntity() {
        AssetEntity a = new AssetEntity();
        a.setId(1L);
        a.setUuid(TEST_UUID);
        a.setTitle(ASSET_TITLE);
        a.setSerialNumber(ASSET_SERIAL_NUMBER);
        a.setArchivedDate(LOCAL_DATE);
        a.setPurchasedDate(LOCAL_DATE);
        return a;
    }
}
