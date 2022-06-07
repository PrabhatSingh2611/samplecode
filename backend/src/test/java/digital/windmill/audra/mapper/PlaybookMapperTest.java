package digital.windmill.audra.mapper;


import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.DateTimeMapperImpl;
import digital.windmill.audra.graphql.mapper.PlaybookMapperImpl;
import digital.windmill.audra.graphql.mapper.ResourceMapper;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.audra.graphql.type.input.CreatePlaybookInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookInput;
import digital.windmill.audra.graphql.type.input.ResourceInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";

    @Mock
    private ResourceMapper resourceMapper;

    @Spy
    private DateTimeMapper dateTimeMapper = new DateTimeMapperImpl();

    @InjectMocks
    PlaybookMapperImpl mapper;

    @Test
    void shouldMap(@Mock Resource resource) {
        when(resource.getId()).thenReturn(TEST_UUID);
        when(resourceMapper.map(any(ResourceEntity.class))).thenReturn(resource);

        var result = mapper.map(createPlaybookEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getName());
        assertEquals(TEST_UUID, result.getId());
        assertEquals(TEST_UUID, result.getImage().getId());
    }

    @Test
    void shouldMapToCreate() {

        var result = mapper.mapToCreate(createPlaybookInput(), createResourceEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getName());
        assertEquals(TEST_UUID, result.getImage().getUuid());
        assertEquals(TEST_UUID, result.getImage().getThumbnail().getUuid());
    }

    @Test
    void shouldMapToPatch() {
        var result = mapper.mapToPatch(
                patchPlaybookInput(), createPlaybookEntity(), createResourceEntity());
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getUuid());
        assertEquals(TEXT, result.getName());
        assertEquals(TEXT, result.getDescription());
        assertEquals(TEST_UUID, result.getImage().getUuid());
        assertEquals(TEST_UUID, result.getImage().getThumbnail().getUuid());
    }

    private PatchPlaybookInput patchPlaybookInput() {
        return PatchPlaybookInput
                .builder()
                .id(TEST_UUID)
                .image(createResourceInput())
                .name(TEXT)
                .description(TEXT)
                .build();
    }

    private PlaybookEntity createPlaybookEntity() {
        var p = new PlaybookEntity();
        p.setId(ID);
        p.setUuid(TEST_UUID);
        p.setName(TEXT);
        p.setDescription(TEXT);
        p.setImage(createResourceEntity());
        return p;
    }


    private ResourceEntity createResourceEntity() {
        ResourceEntity r = new ResourceEntity();
        r.setUuid(TEST_UUID);
        r.setThumbnail(createThumbNail());
        r.setOuterReference(TEXT);
        return r;
    }

    private ResourceEntity createThumbNail() {
        ResourceEntity r = new ResourceEntity();
        r.setUuid(TEST_UUID);
        r.setOuterReference(TEXT);
        return r;
    }

    private CreatePlaybookInput createPlaybookInput() {
        return CreatePlaybookInput
                .builder()
                .name(TEXT)
                .description(TEXT)
                .image(createResourceInput())
                .build();
    }

    private ResourceInput createResourceInput() {
        var r = new ResourceInput();
        r.setId(TEST_UUID);
        return r;
    }
}
