package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.graphql.type.Announcement;
import digital.windmill.audra.graphql.type.input.CreateAnnouncementInput;
import digital.windmill.audra.graphql.type.input.UpdateAnnouncementInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, EmployeeMapper.class}, imports = {UUID.class})
public interface AnnouncementMapper {

    Announcement mapAnnouncementEntityToAnnouncement(AnnouncementEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    AnnouncementEntity mapCreateAnnounceInputToAnnouncementEntity(CreateAnnouncementInput input);

    @Mapping(target = "uuid", ignore = true)
    AnnouncementEntity mapUpdateAnnouncementInputToAnnouncementEntity(UpdateAnnouncementInput input,
                                                                      @MappingTarget AnnouncementEntity entity);
}
