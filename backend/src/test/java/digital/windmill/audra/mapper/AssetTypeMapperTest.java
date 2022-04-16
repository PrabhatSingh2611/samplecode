package digital.windmill.audra.mapper;


import digital.windmill.audra.dao.entity.AssetTypeEntity;
import digital.windmill.audra.graphql.mapper.AssetTypeMapperImpl;
import digital.windmill.audra.graphql.type.AssetType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AssetTypeMapperTest {
    @InjectMocks
    AssetTypeMapperImpl mapper;

    private static final Long ID = 1l;
    private static final UUID TEST_UUID = UUID.fromString("5478b586-e607-4448-ac05-3e5f2adbbc1b");
    private static final String TITLE = "Laptops";
    private static final String ICON = "https://google.com/laptops";

    @Test
    void shouldMap(){
       AssetType actual =  mapper.map(createAssetTypeEntity());
        assertAll(
                () -> assertEquals(TITLE, actual.getTitle()),
                () -> assertEquals(TEST_UUID, actual.getUuid()),
                () -> assertEquals(ICON, actual.getIcon())
        );
    }

    private AssetTypeEntity createAssetTypeEntity() {
        return AssetTypeEntity.builder()
                .uuid(TEST_UUID)
                .icon(ICON)
                .id(ID)
                .title(TITLE)
                .build();
    }
}
