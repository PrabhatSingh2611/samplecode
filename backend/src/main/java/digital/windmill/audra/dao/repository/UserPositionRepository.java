package ch.windmill.audra.dao.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.windmill.audra.dao.entity.UserPositionEntity;

public interface UserPositionRepository extends JpaRepository<UserPositionEntity, Long> {

    UserPositionEntity findByUuid(UUID uuid);

}
