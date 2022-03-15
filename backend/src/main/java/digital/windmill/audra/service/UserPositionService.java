package ch.windmill.audra.service;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ch.windmill.audra.dao.entity.UserPositionEntity;
import ch.windmill.audra.dao.repository.UserPositionRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserPositionService {

    private UserPositionRepository repository;

    public UserPositionEntity findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }

}
