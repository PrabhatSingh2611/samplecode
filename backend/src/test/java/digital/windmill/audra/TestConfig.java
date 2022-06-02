package digital.windmill.audra;

import digital.windmill.audra.storage.impl.AzureBlobStorageServiceImpl;
import digital.windmill.audra.storage.impl.MongoDbStorageServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@ActiveProfiles("test")
public class TestConfig {

    @Bean
    AzureBlobStorageServiceImpl azureBlobStorageServiceImpl() {
        return Mockito.mock(AzureBlobStorageServiceImpl.class);
    }

    @Bean
    @Primary
    MongoDbStorageServiceImpl mongoDbStorageServiceImpl() {
        return Mockito.mock(MongoDbStorageServiceImpl.class);
    }

}
