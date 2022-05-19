package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import digital.windmill.audra.dao.entity.enums.PolicyStatus;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.PolicyMapperImpl;
import digital.windmill.audra.graphql.mapper.ResourceMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.audra.graphql.type.input.CreatePolicyInput;
import digital.windmill.audra.graphql.type.input.ResourceInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyMapperImplTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String TEXT = "9AMj3X";
    private static final PolicyStatus STATUS = PolicyStatus.PUBLISHED;
    private static final EmployeeRole ROLE = EmployeeRole.EMPLOYEE;
    private static final ZoneId zone = ZoneId.systemDefault();
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.of(2020, 2, 12, 7, 55, 20, 100, zone);
    private final static Instant LOCAL_DATE = DATE_TIME.toInstant();
    @InjectMocks
    PolicyMapperImpl mapper;
    @Mock
    private DateTimeMapper dateTimeMapper;
    @Mock
    private ResourceMapper resourceMapper;
    @Mock
    private EmployeeMapper employeeMapper;

    @Test
    void shouldMapCreatePolicyInputToPolicyEntity() {

        var result = mapper.mapCreatePolicyInputToPolicyEntity(createEmployeeEntity(),
                createResourceEntity(), pojoCreatePolicyInput());
        assertNotNull(result);
        assertEquals(TEST_UUID, result.getFile().getUuid());
        assertEquals(TEXT, result.getFile().getOuterReference());
        assertEquals(TEST_UUID, result.getFile().getThumbnail().getUuid());
        assertEquals(TEXT, result.getFile().getThumbnail().getOuterReference());
        assertEquals(TEXT, result.getFile().getOuterReference());
    }

    @Test
    void shouldMapPolicyEntityToPolicy() {
        when(dateTimeMapper.map(any(Instant.class))).thenReturn(DATE_TIME);
        when(employeeMapper.mapEmployeeEntityToEmployee(any(EmployeeEntity.class))).thenReturn(createEmployee());
        when(resourceMapper.map(any(ResourceEntity.class))).thenReturn(createResource());

        var result = mapper.mapPolicyEntityToPolicy(createPolicyEntity());

        assertNotNull(result);

        // TODO: add checks for all fields
    }

    private PolicyEntity createPolicyEntity() {
        PolicyEntity e = new PolicyEntity();
        e.setUuid(TEST_UUID);
        e.setOwner(createEmployeeEntity());
        e.setStatus(STATUS);
        e.setTitle(TEXT);
        e.setPublicationDate(LOCAL_DATE);
        e.setFile(createResourceEntity());
        return e;
    }

    private Employee createEmployee() {
        Employee e = new Employee();
        e.setBirthday(DATE_TIME);
        e.setRole(EmployeeRole.EMPLOYEE);
        e.setFirstName(TEXT);
        e.setLastName(TEXT);
        e.setLocation(createLocation());
        e.setPosition(createPosition());
        e.setId(TEST_UUID);
        e.setReportingManager(new Employee());
        return e;

    }

    private EmployeePosition createPosition() {
        return EmployeePosition.builder()
                .id(TEST_UUID)
                .name(TEXT)
                .build();
    }

    private Location createLocation() {
        Location e = new Location();
        e.setId(TEST_UUID);
        e.setName(TEXT);
        return e;
    }

    private Resource createResource() {
        Resource r = new Resource();
        r.setId(TEST_UUID);
        r.setThumbnail(TEXT);
        r.setUrl(TEXT);
        return r;
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setUuid(TEST_UUID);
        e.setFirstName(TEXT);
        e.setLastName(TEXT);
        e.setBirthday(LOCAL_DATE);
        e.setPosition(createPositionEntity());
        e.setLocation(createLocationEntity());

        return e;
    }

    private LocationEntity createLocationEntity() {
        LocationEntity e = new LocationEntity();
        e.setName(TEXT);
        return e;
    }

    private EmployeePositionEntity createPositionEntity() {
        return EmployeePositionEntity.builder()
                .uuid(TEST_UUID)
                .name(TEXT)
                .build();
    }

    private CreatePolicyInput pojoCreatePolicyInput() {
        return CreatePolicyInput.builder()
                .file(createResourceInputFile())
                .title(TEXT)
                .status(STATUS)
                .build();
    }

    private ResourceInput createResourceInputFile() {
        ResourceInput r = new ResourceInput();
        r.setId(TEST_UUID);
        return r;
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
}