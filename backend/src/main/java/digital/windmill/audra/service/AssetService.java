package digital.windmill.audra.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<AssetEntity> findAll() {
        return repo.findAll(Pageable.unpaged());
    }
}
