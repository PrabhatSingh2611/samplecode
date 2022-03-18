package digital.windmill.audra.graphql.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DateTimeMapper {
    
    private static final ZoneId UTC_ZONE = ZoneId.of("UTC");

    public ZonedDateTime map(Instant instant) {
        return instant == null ? null
                : instant.atZone(UTC_ZONE);
    }

}
