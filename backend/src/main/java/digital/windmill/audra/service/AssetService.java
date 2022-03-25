package digital.windmill.audra.service;

import digital.windmill.audra.dao.AssetSpecification;
import digital.windmill.audra.graphql.type.input.AssetInput;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import digital.windmill.audra.dao.entity.AssetEntity;
import digital.windmill.audra.dao.repository.AssetRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AssetService {

    private AssetRepository repo;
    
    public AssetEntity findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Page<AssetEntity> findAll(AssetInput input) {
        var spec = AssetSpecification.assets(input);
        return repo.findAll(spec.getKey(), spec.getValue());
    }
}
