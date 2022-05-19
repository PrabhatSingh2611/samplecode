package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.ObjectiveFacade;
import digital.windmill.audra.graphql.resolver.objective.ObjectiveResolver;
import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.ObjectiveInput;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObjectiveResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private ObjectiveFacade objectiveFacade;

    @InjectMocks
    ObjectiveResolver objectiveResolver;

    @Test
    void shouldGetObjective(@Mock ObjectiveInput objectiveInput,
                            @Mock Objective objective) {
        when(objectiveInput.getId()).thenReturn(TEST_UUID);
        when(objectiveFacade.findObjectiveByUuid(any(UUID.class))).thenReturn(objective);

        var result = objectiveResolver.objective(objectiveInput);
        assertNotNull(result);
        assertSame(objective, result.getItem());
    }

    @Test
    void shouldGetAllObjectives(@Mock ObjectivesInput input,
                                @Mock Objective objective) {

        Page<Objective> pagedResponse = createOneItemPage(objective);
        when(objectiveFacade.getObjectives(any(ObjectivesInput.class))).thenReturn(pagedResponse);

        var result = objectiveResolver.objectives(input);
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}