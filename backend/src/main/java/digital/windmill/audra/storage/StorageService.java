package digital.windmill.audra.storage;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StorageService {

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
        return null;
    }
    
    private Query queryById(String resourceId) {
        return new Query(Criteria.where("_id").is(resourceId));
    }

}
