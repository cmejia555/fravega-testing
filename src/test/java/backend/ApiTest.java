package backend;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;


public class ApiTest extends BaseApiTest {

    @Test
    public void test_01_basicApiTest() {
        String endpoint = "/breweries/autocomplete";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, endpoint, queries);
        response.prettyPrint();
    }
}
