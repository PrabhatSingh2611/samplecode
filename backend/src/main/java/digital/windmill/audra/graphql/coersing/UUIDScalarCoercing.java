package digital.windmill.audra.graphql.coersing;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import java.util.UUID;

public class UUIDScalarCoercing implements Coercing<UUID, String> {

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        return dataFetcherResult.toString();
    }

    @Override
    public UUID parseValue(Object input) throws CoercingParseValueException {
        try {
            return UUID.fromString(input.toString());
        } catch (RuntimeException e) {
            throw new CoercingParseValueException("Cannot cast value " + input + " into UUID");
        }
    }

    @Override
    public UUID parseLiteral(Object input) throws CoercingParseLiteralException {
        String value = ((StringValue) input).getValue();
        try {
            return UUID.fromString(value);
        } catch (RuntimeException e) {
            throw new CoercingParseLiteralException("Cannot cast value " + value + " into UUID");
        }
    }
}
