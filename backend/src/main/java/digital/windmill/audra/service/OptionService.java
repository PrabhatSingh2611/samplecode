package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.OptionEntity;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OptionService {

    /**
     * This method will return a list of Options.
     *
     * @param uuid for query result
     * @return a list of OptionEntity including pagination
     */
    Page<OptionEntity> findAllOptions(UUID uuid);
}
