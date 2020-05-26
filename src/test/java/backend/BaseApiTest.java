package backend;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BaseApiTest {

    protected RequestSpecification generateRequest(String endpoint, HashMap<String, String> query) {
        RequestSpecification requestSpecification = given().log().all()
                .baseUri("https://api.openbrewerydb.org")
                .contentType(ContentType.JSON)
                .basePath(endpoint);

        if (query != null) {
            requestSpecification.queryParams(query);
        }

        return requestSpecification;
    }

    protected Response sendRequest(Method method, RequestSpecification requestSpecification) {
        return requestSpecification.request(method);
    }

    public Response executeRequest(Method method, String endpoint, HashMap<String, String> query) {
        RequestSpecification requestSpecification = generateRequest(endpoint, query);
        Response response = sendRequest(method, requestSpecification);
        return response;
    }

    public Response executeRequest(Method method, String endpoint) {
        RequestSpecification requestSpecification = generateRequest(endpoint, null);
        Response response = sendRequest(method, requestSpecification);
        return response;
    }

}
