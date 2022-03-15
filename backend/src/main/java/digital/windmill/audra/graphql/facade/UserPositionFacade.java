package ch.windmill.audra.graphql.facade;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ch.windmill.audra.graphql.mapper.UserPositionMapper;
import ch.windmill.audra.graphql.type.UserPosition;
import ch.windmill.audra.service.UserPositionService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserPositionFacade {

    private UserPositionService service;
    private UserPositionMapper mapper;

    public UserPosition findById(UUID id) {
        return mapper.map(service.findByUuid(id));
    }

}
