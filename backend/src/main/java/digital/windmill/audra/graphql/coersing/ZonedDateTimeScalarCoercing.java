package digital.windmill.audra.graphql.coersing;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

public class ZonedDateTimeScalarCoercing implements Coercing<ZonedDateTime, String> {

    private static final ZoneId UTC_ZONE = ZoneId.of("UTC");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof ZonedDateTime dt) {
            return dt.withZoneSameInstant(UTC_ZONE)
                    .format(FORMATTER);
        }
        throw new CoercingSerializeException("Could not serialize as ZonedDateTime object - " + dataFetcherResult);
    }

    @Override
    public ZonedDateTime parseValue(Object input) throws CoercingParseValueException {
        try {
            return ZonedDateTime.parse(input.toString(), FORMATTER);
        } catch (RuntimeException e) {
            throw new CoercingParseValueException("Cannot cast value " + input + " into ZonedDateTime");
        }
    }

    @Override
    public ZonedDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
        String value = ((StringValue) input).getValue();
        try {
            return ZonedDateTime.parse(value, FORMATTER);
        } catch (RuntimeException e) {
            throw new CoercingParseLiteralException("Cannot parse value " + value + " into ZonedDateTime");
        }
    }
}
