package digital.windmill.audra.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.language.SourceLocation;

import java.util.ArrayList;
import java.util.List;


public class DataNotFoundException extends GraphQLException implements GraphQLError {

    public DataNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return new ArrayList<>();
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

}
