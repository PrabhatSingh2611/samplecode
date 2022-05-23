package digital.windmill.audra.graphql.coersing;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.net.MalformedURLException;
import java.net.URL;


public class URLScalarCoercing implements Coercing<URL, String> {

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        return dataFetcherResult.toString();
    }

    @Override
    public URL parseValue(Object input) throws CoercingParseValueException {
        try {
            return new URL(input.toString()) ;
        } catch (RuntimeException | MalformedURLException e) {
            throw new CoercingParseValueException("Cannot cast value " + input + " into URL");
        }
    }

    @Override
    public URL parseLiteral(Object input) throws CoercingParseLiteralException {
        String value = ((StringValue) input).getValue();
        try {
            return new URL(value);
        } catch (RuntimeException | MalformedURLException e) {
            throw new CoercingParseLiteralException("Cannot cast value " + value + " into URL");
        }
    }
}
