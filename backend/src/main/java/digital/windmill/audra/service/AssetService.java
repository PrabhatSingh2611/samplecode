package digital.windmill.audra.service;

import digital.windmill.audra.dao.AssetSpecification;
import digital.windmill.audra.graphql.type.input.AssetInput;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.repository.AssetRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AssetService {

    private AssetRepository repo;

    public AssetEntity findByUuid(UUID uuid) {
        return repo.findByUuid(uuid).orElse(null);
    }

    public Page<AssetEntity> findAll(AssetInput input) {
        var spec = AssetSpecification.assets(input);
        return repo.findAll(spec.getKey(), spec.getValue());
    }
}
