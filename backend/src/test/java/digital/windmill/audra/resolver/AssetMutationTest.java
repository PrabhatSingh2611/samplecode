package digital.windmill.audra.resolver;

import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.graphql.facade.AssetFacade;
import digital.windmill.audra.graphql.resolver.asset.AssetMutationResolver;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.AssetType;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.UpdateAssetInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetMutationTest {

    private static final UUID TEST_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");
    private static final Long ID = 1L;
    private static final String NAME = "Name";
    private static final EmployeeRole ROLE = EmployeeRole.EMPLOYEE;
    private static final String SERIAL = "123QWE";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();

    @Mock
    private AssetFacade assetFacade;

    @InjectMocks
    private AssetMutationResolver assetMutationResolver;

    @Test
    void shouldCreateAsset(@Mock CreateAssetInput createAssetInput) {
        when(assetFacade.createAsset(any(CreateAssetInput.class))).thenReturn(createAsset());

        var actualResult = assetMutationResolver.createAsset(createAssetInput);
        assertNotNull(actualResult);
        assertEquals(TEST_UUID, actualResult.getItem().getUuid());
        assertEquals(TEST_UUID, actualResult.getItem().getType().getUuid());
        assertEquals(TEST_UUID, actualResult.getItem().getEmployee().getUuid());
        assertEquals(NAME, actualResult.getItem().getTitle());
        assertEquals(SERIAL, actualResult.getItem().getSerial());
        assertEquals(DATE_TIME, actualResult.getItem().getArchivedDate());
        assertEquals(DATE_TIME, actualResult.getItem().getPurchasedDate());
        assertEquals(DATE_TIME, actualResult.getItem().getNextActionDate());
    }

    @Test
    void shouldUpdateAsset(@Mock UpdateAssetInput updateAssetInput) {
        when(assetFacade.updateAsset(any(UpdateAssetInput.class))).thenReturn(createAsset());

        var actualResult = assetMutationResolver.updateAsset(updateAssetInput);
        assertNotNull(actualResult);
        assertEquals(TEST_UUID, actualResult.getItem().getUuid());
        assertEquals(TEST_UUID, actualResult.getItem().getType().getUuid());
        assertEquals(TEST_UUID, actualResult.getItem().getEmployee().getUuid());
        assertEquals(NAME, actualResult.getItem().getTitle());
        assertEquals(SERIAL, actualResult.getItem().getSerial());
        assertEquals(DATE_TIME, actualResult.getItem().getArchivedDate());
        assertEquals(DATE_TIME, actualResult.getItem().getPurchasedDate());
        assertEquals(DATE_TIME, actualResult.getItem().getNextActionDate());
    }

    private Asset createAsset() {
        return Asset
                .builder()
                .uuid(TEST_UUID)
                .title(NAME)
                .serial(SERIAL)
                .employee(createEmployee())
                .type(createAssetType())
                .archivedDate(DATE_TIME)
                .purchasedDate(DATE_TIME)
                .nextActionDate(DATE_TIME)
                .build();
    }

    private Employee createEmployee() {
        return Employee.builder()
                .uuid(TEST_UUID)
                .firstName(NAME)
                .lastName(NAME)
                .role(ROLE)
                .birthday(DATE_TIME)
                .build();
    }

    private AssetType createAssetType() {
        return AssetType.builder()
                .uuid(TEST_UUID)
                .title(NAME)
                .build();
    }
}
