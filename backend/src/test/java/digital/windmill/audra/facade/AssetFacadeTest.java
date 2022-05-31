package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.facade.impl.AssetFacadeImpl;
import digital.windmill.audra.graphql.mapper.AssetMapper;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.input.ArchiveAssetInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.graphql.type.input.CreateAssetInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.service.AssetService;
import digital.windmill.audra.service.AssetTypeService;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.impl.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssetFacadeTest {

    private static final UUID ASSET_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final UUID ASSET_TYPE_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");

    @Mock
    private AssetService assetService;

    @Mock
    private AssetTypeService assetTypeService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private LocationService locationService;

    @InjectMocks
    private AssetFacadeImpl facade;
    @Mock
    private AssetMapper assetMapper;

    @Test
    void shouldReturnAssetByUuid(@Mock AssetEntity assetEntity, @Mock Asset asset) {
        when(assetService.findAssetByUuid(any(UUID.class))).thenReturn(assetEntity);
        when(assetMapper.mapAssetEntityToAsset(any(AssetEntity.class))).thenReturn(asset);
        var result = facade.findAssetByUuid(ASSET_UUID);
        assertNotNull(result);
        Assertions.assertSame(asset, result);
    }

    @Test
    void shouldFindAllAssets(@Mock AssetEntity assetEntity, @Mock Asset asset, @Mock AssetsInput assetsInput) {
        Page<AssetEntity> assetEntityPage = createOneItemPage(assetEntity);
        when(assetService.findAll(any(AssetsInput.class))).thenReturn(assetEntityPage);
        when(assetMapper.mapAssetEntityToAsset(any(AssetEntity.class))).thenReturn(asset);
        var result = facade.findAllAssets(assetsInput);
        assertNotNull(result);
        assertSame(asset, result.getContent().get(0));
    }

    @Test
    void shouldCreateAsset(@Mock AssetEntity assetEntity
            , @Mock Asset asset
            , @Mock CreateAssetInput input
            , @Mock NodeInput nodeInput
            , @Mock AssetTypeEntity assetTypeEntity
            , @Mock LocationEntity locationEntity
            , @Mock EmployeeEntity employeeEntity) {
        when(input.getType()).thenReturn(nodeInput);
        when(input.getAssignee()).thenReturn(nodeInput);
        when(input.getLocation()).thenReturn(nodeInput);
        when(input.getType().getId()).thenReturn(ASSET_TYPE_UUID);
        when(assetTypeService.findAssetByUuid(any(UUID.class))).thenReturn(assetTypeEntity);
        when(locationService.findLocationByUuid(any(UUID.class))).thenReturn(locationEntity);
        when(employeeService.findEmployeeByUuid(any(UUID.class))).thenReturn(employeeEntity);
        when(assetMapper.mapAssetCreateInputToAssetEntity(any(CreateAssetInput.class)
                , any(AssetTypeEntity.class), any(EmployeeEntity.class), any(LocationEntity.class)))
                .thenReturn(assetEntity);
        when(assetService.createOrUpdateAsset(any(AssetEntity.class))).thenReturn(assetEntity);
        when(assetMapper.mapAssetEntityToAsset(any(AssetEntity.class))).thenReturn(asset);
        var result = facade.createAsset(input);
        assertNotNull(result);
        assertSame(asset, result);
    }

    @Test
    void shouldUpdateAssetAsArchive(@Mock AssetEntity assetEntity, @Mock Asset asset, @Mock ArchiveAssetInput input
            , @Mock NodeInput nodeInput) {
        when(input.getAsset()).thenReturn(nodeInput);
        when(input.getAsset().getId()).thenReturn(ASSET_UUID);
        when(assetService.findAssetByUuid(any(UUID.class))).thenReturn(assetEntity);
        when(assetMapper.mapArchiveAssetInputToAssetEntity(any(ArchiveAssetInput.class)
                , any(AssetEntity.class))).thenReturn(assetEntity);
        when(assetService.createOrUpdateAsset(any(AssetEntity.class))).thenReturn(assetEntity);
        when(assetMapper.mapAssetEntityToAsset(any(AssetEntity.class))).thenReturn(asset);
        var result = facade.updateAssetAsArchive(input);
        assertNotNull(result);
        assertSame(asset, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
