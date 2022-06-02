package digital.windmill.audra.storage.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import digital.windmill.audra.storage.StorableObject;
import digital.windmill.audra.storage.StorageService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
@Primary
public class MongoDbStorageServiceImpl implements StorageService {

    private final GridFsTemplate gridFsTemplate;

    public String store(StorableObject objectToStore) {
        ObjectId response = gridFsTemplate.store(objectToStore.getStream(),
                objectToStore.getFileName(),
                objectToStore.getContentType(),
                prepareMetadata(objectToStore));
        return response.toString();
    }

    public Optional<Resource> findById(String resourceId) {
        return Optional.ofNullable(resourceId)
                .filter(StringUtils::isNotBlank)
                .map(this::queryById)
                .map(gridFsTemplate::findOne)
                .map(gridFsTemplate::getResource);
    }

    private DBObject prepareMetadata(StorableObject object) {
        var metadata = new BasicDBObject();
        if (object.getMetadata() != null) {
            metadata.putAll(object.getMetadata());
        }
        return metadata;
    }

    private Query queryById(String resourceId) {
        return new Query(Criteria.where("_id").is(resourceId));
    }

}
