package digital.windmill.audra;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql(scripts = "/db/delete-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
abstract class PostgresIntegrationTest {

    static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse(PostgreSQLContainer.IMAGE))
                .withPassword("postgres")
                .withUsername("postgres");
        postgreSQLContainer.withReuse(true);
        postgreSQLContainer.start();
    }

    @BeforeAll
    public static void beforeAll() {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void datasourceConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.liquibase.url", postgreSQLContainer::getJdbcUrl);
    }
}
