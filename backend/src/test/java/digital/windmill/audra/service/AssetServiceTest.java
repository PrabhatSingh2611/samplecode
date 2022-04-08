package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.repository.AssetRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import liquibase.pro.packaged.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
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
    private AssetService service;

    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String ASSET_TITLE = "Asset title";
    private static final String ASSET_SERIAL_NUMBER = "40aab8f6";
    private final static Instant LOCAL_DATE = Instant.now();


    @Test
    void shouldReturnAllAssetById() {

        when(assetRepository.findByUuid(any(UUID.class))).thenReturn(createAssetEntity());

        var result = service.findByUuid(TEST_UUID);

        assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals(TEST_UUID, result.getUuid());
    }

    @Test
    void shouldThrowNPEWhenAssetInputIsNull() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findByUuid(null));
    }


    private Optional<AssetEntity> createAssetEntity() {
        AssetEntity a = new AssetEntity();
        a.setId(1L);
        a.setUuid(TEST_UUID);
        a.setTitle(ASSET_TITLE);
        a.setSerialNumber(ASSET_SERIAL_NUMBER);
        a.setArchivedDate(LOCAL_DATE);
        a.setPurchasedDate(LOCAL_DATE);
        return Optional.of(a);
    }

}
