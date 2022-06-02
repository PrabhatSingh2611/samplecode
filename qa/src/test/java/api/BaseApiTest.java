package api;

import api.pojo.GraphQLQuery;
import api.pojo.QueryResponse;
import api.pojo.QueryVariables;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import api.enums.Queries;
import utils.Config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    Config config = new Config();

    public static RequestSpecification req;
    public GraphQLQuery queryData = new GraphQLQuery();
    public RequestSpecification res;
    public QueryVariables queryVariables = new QueryVariables();
    public Response response;

    public RequestSpecification requestSpecification() throws IOException {

        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(config.getApiURL())
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

    public String dataMapper(Object data) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(data);
    }

    public void setQuery(Queries enumQuery, QueryVariables queryVariable) throws JsonProcessingException, IOException {
        queryData.setQuery(enumQuery.getQuery());
        queryData.setVariables(queryVariable);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        res = given().spec(requestSpecification()).body(ow.writeValueAsString(queryData));
    }

    public QueryResponse getRespose() throws JsonMappingException, JsonProcessingException {
        response = res.when().post();
        return new ObjectMapper().readValue(response.asPrettyString(), QueryResponse.class);
    }

    public QueryResponse sendRequest(Queries enumQuery, QueryVariables queryVariable)  throws JsonProcessingException, IOException {
        queryData.setQuery(enumQuery.getQuery());
        queryData.setVariables(queryVariable);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        RequestSpecification requestSpecification = given().spec(requestSpecification()).body(ow.writeValueAsString(queryData));
        Response rspns = requestSpecification.when().post();        
        return new ObjectMapper().readValue(rspns.asPrettyString(), QueryResponse.class);
    }
}
