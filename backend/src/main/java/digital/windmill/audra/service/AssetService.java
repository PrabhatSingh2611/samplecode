package digital.windmill.audra.service;

import digital.windmill.audra.dao.AssetSpecification;
import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.repository.AssetRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetService {

    private AssetRepository repo;

    public AssetEntity findByUuid(UUID uuid) {
        return repo.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Asset not found.")
        );
    }

    public Page<AssetEntity> findAll(AssetsInput input) {
        var spec = AssetSpecification.assets(input);
        return repo.findAll(spec.getKey(), spec.getValue());
    }
}
