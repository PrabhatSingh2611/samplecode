package digital.windmill.audra.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoClientConfiguration {

    private static final String FILE_STORAGE_DB = "files";
    @Value("${storage.mongo.url}")
    private String mongoUrl;

    @Bean
    public GridFsTemplate gridFsTemplate(MappingMongoConverter mongoConverter) throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mongoConverter);
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUrl);
    }
    
    @Override
    protected String getDatabaseName() {
        return FILE_STORAGE_DB;
    }

}
