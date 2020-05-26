package backend;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;


public class ApiTest extends BaseApiTest {

    @Test
    public void test_01_basicApiTest() {
        String endpoint = "/breweries/autocomplete";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, endpoint, queries);
        response.prettyPrint();
    }

    @Test
    public void test_02_filterResults() {
        String filter = "Lagunitas Brewing Co";
        String endpoint = "/breweries/autocomplete";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");


        Response response = executeRequest(Method.GET, endpoint, queries);

        List<String> list = response.body().jsonPath().param("name", filter).getList("findAll{it -> it.name == name}");
        System.out.println(list);
    }
}
