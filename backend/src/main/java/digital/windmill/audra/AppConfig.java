package ch.windmill.audra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.windmill.audra.graphql.coersing.UUIDScalarCoercing;
import ch.windmill.audra.graphql.type.UserPosition;
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
    public SchemaParserDictionary schemaParserDictionary() {
        return new SchemaParserDictionary()
                .add(UserPosition.class);
    }

}
