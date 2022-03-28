package digital.windmill.audra;

import digital.windmill.audra.graphql.type.LeaveRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import digital.windmill.audra.graphql.coersing.UUIDScalarCoercing;
import digital.windmill.audra.graphql.coersing.ZonedDateTimeScalarCoercing;
import digital.windmill.audra.graphql.type.Asset;
import graphql.kickstart.tools.SchemaParserDictionary;
import graphql.schema.GraphQLScalarType;

@Configuration
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

}
