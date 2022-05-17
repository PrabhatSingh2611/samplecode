package digital.windmill.audra;

import digital.windmill.audra.graphql.coersing.UUIDScalarCoercing;
import digital.windmill.audra.graphql.coersing.ZonedDateTimeScalarCoercing;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.properties.ResourceProperties;
import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.kickstart.tools.SchemaParserDictionary;
import graphql.schema.GraphQLScalarType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ResourceProperties.class})
public class AppConfig {

    @Bean
    public GraphQLScalarType getUUIDScalar() {
        return GraphQLScalarType.newScalar()
                .name("UUID")
                .description("UUID")
                .coercing(new UUIDScalarCoercing())
                .build();
    }

    @Bean
    public GraphQLScalarType getZonedDateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("ZonedDateTime")
                .description("ZonedDateTime")
                .coercing(new ZonedDateTimeScalarCoercing())
                .build();
    }

    @Bean
    public SchemaParserDictionary schemaParserDictionary() {
        return new SchemaParserDictionary()
                .add(Asset.class)
                .add(LeaveRequest.class);
    }

    @Bean
    public GraphQLScalarType uploadScalar() {
        return ApolloScalars.Upload;
    }

}
