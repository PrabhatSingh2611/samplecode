package digital.windmill.audra.resolver;

import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.resolver.objective.ObjectiveMutationResolver;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.Locations;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.DeleteObjectiveInput;
import digital.windmill.audra.graphql.type.input.UpdateObjectiveInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveMutationResolverTest {
    @Mock
    private ObjectiveFacade facade;
    @InjectMocks
    ObjectiveMutationResolver resolver;
    @Test
    void shouldCreateObjective(@Mock CreateObjectiveInput input,
                               @Mock Objective objective) {
        when(facade.createObjective(any(CreateObjectiveInput.class))).thenReturn(objective);

        var result = resolver.createObjective(input);
        assertNotNull(result);
        assertSame(objective, result.getItem());
    }

    @Test
    void shouldUpdateObjective(@Mock UpdateObjectiveInput input,
                               @Mock Objective objective) {
        when(facade.updateObjective(any(UpdateObjectiveInput.class)))
                .thenReturn(objective);

        var result = resolver.updateObjective(input);
        assertNotNull(result);
        assertSame(objective, result.getItem());
    }

    @Test
    void shouldDeleteObjective(@Mock DeleteObjectiveInput input,
                               @Mock Objective objective) {
        when(facade.deleteObjective(any(DeleteObjectiveInput.class)))
                .thenReturn(objective);

        var result = resolver.deleteObjective(input);
        assertNotNull(result);
        assertSame(objective, result.getObjective());
    }
}