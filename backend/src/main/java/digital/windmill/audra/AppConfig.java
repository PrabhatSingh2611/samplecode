package digital.windmill.audra;

import digital.windmill.audra.graphql.coersing.URLScalarCoercing;
import digital.windmill.audra.graphql.type.Asset;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.LeaveTypeEndOfYearAction;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.PlaybookResource;
import digital.windmill.audra.graphql.type.PlaybookStep;
import digital.windmill.audra.graphql.type.PlaybookTask;
import digital.windmill.audra.graphql.type.PlaybookTaskList;
import digital.windmill.audra.graphql.type.PlaybookVideo;
import digital.windmill.audra.properties.ResourceProperties;
import digital.windmill.i18n.I18nConfig;
import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.kickstart.tools.SchemaParserDictionary;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(I18nConfig.class)
@EnableConfigurationProperties({ResourceProperties.class})
public class AppConfig {

    @Bean
    public SchemaParserDictionary schemaParserDictionary() {
        return new SchemaParserDictionary()
                .add(Asset.class)
                .add(LeaveRequest.class)
                .add(LeaveTypeEndOfYearAction.class)
                .add(Playbook.class)
                .add(PlaybookStep.class)
                .add(PlaybookVideo.class)
                .add(PlaybookResource.class)
                .add(PlaybookTask.class)
                .add(PlaybookTaskList.class)
                ;
    }

    public GraphQLScalarType getURLScalar() {
        return GraphQLScalarType.newScalar()
                .name("URL")
                .description("URL")
                .coercing(new URLScalarCoercing())
                .build();
    }

    @Bean
    public GraphQLScalarType uploadScalar() {
        return ApolloScalars.Upload;
    }

    @Bean
    public GraphQLScalarType uuidType() {
        return ExtendedScalars.UUID;
    }

    @Bean
    public GraphQLScalarType dateTimeType() {
        return ExtendedScalars.DateTime;
    }

    @Bean
    public GraphQLScalarType urlType() {
        return ExtendedScalars.Url;
    }

}
