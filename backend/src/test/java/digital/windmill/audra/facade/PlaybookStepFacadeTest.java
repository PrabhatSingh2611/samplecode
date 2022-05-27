package digital.windmill.audra.facade;

import digital.windmill.audra.graphql.facade.impl.PlaybookStepFacade;
import digital.windmill.audra.graphql.mapper.PlaybookStepMapper;
import digital.windmill.audra.service.impl.PlaybookStepService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PlaybookStepFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private PlaybookStepService playbookStepService;
    @Mock
    private PlaybookStepMapper playbookStepMapper;
    @InjectMocks
    PlaybookStepFacade facade;


}
