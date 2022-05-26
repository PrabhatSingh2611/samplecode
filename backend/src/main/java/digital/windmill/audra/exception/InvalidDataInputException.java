package digital.windmill.audra.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.language.SourceLocation;

import java.util.ArrayList;
import java.util.List;


public class InvalidDataInputException extends GraphQLException implements GraphQLError {

    public InvalidDataInputException(String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return new ArrayList<>();
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.ValidationError;
    }

}
