package backend;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import static org.hamcrest.Matchers.*;


public class ApiTest extends BaseApiTest {

    //@Test
    public void test_01_basicApiTest() {
        String endpoint = "/breweries/autocomplete";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, endpoint, queries);
        response.prettyPrint();
    }

    //@Test
    public void test_02_filterResults() {
        String filter = "Lagunitas Brewing Co";
        String endpoint = "/breweries/autocomplete";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, endpoint, queries);

        List<String> list = response.body().jsonPath().param("name", filter).getList("findAll{it -> it.name == name}");
        System.out.println(list);
    }

    //@Test
    public void test_03_getDetails() {
        String filter = "Lagunitas Brewing Co";
        String endpoint = "/breweries/autocomplete";
        String detailsEndPoint = "/breweries/%s";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, endpoint, queries);

        List<HashMap<String, String>> list = response.body().jsonPath().param("barName", filter).getList("findAll{it -> it.name == barName}");

        for (HashMap<String, String> map : list) {
            Response detailsResponse = executeRequest(Method.GET, String.format(detailsEndPoint, map.get("id")));
            detailsResponse.prettyPrint();
        }
    }

    @Test
    public void test_04_validateBrewery() {
        String filter = "Lagunitas Brewing Co";
        String endpoint = "/breweries/autocomplete";
        String detailsEndPoint = "/breweries/%s";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, endpoint, queries);

        List<HashMap<String, String>> list = response.body().jsonPath().param("breweryName", filter).getList("findAll{it -> it.name == breweryName}");

        for (HashMap<String, String> map : list) {
            Response detailsResponse = executeRequest(Method.GET, String.format(detailsEndPoint, map.get("id")));

            if (detailsResponse.path("state").equals("California")) {
                detailsResponse.then()
                        .body("id", equalTo(761))
                        .body("name", equalTo("Lagunitas Brewing Co"))
                        .body("street", equalTo("1280 N McDowell Blvd"))
                        .body("phone", equalTo("7077694495"));
            }
        }
    }
}
