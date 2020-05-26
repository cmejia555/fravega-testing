package backend;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

@DisplayName("Breweries Api Test")
@Epic("Backend")
@Feature("Breweries")
public class ApiTest extends BaseApiTest {

    @Test
    @DisplayName("Ejecucion de request basico")
    public void test_01_basicApiTest() {
        String endpoint = "/breweries/autocomplete";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, endpoint, queries);
        response.prettyPrint();
    }

    @Test
    @DisplayName("Filtra los resultados del response por un campo")
    public void test_02_filterResults() {
        String filter = "Lagunitas Brewing Co";
        String endpoint = "/breweries/autocomplete";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, endpoint, queries);

        List<String> list = response.body().jsonPath().param("name", filter).getList("findAll{it -> it.name == name}");
        System.out.println(list);
    }

    @Test
    @DisplayName("Muestra los detalles del resultado filtrado")
    public void test_03_getDetails() {
        String filter = "Lagunitas Brewing Co";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, "/breweries/autocomplete", queries);

        List<HashMap<String, String>> list = response.body().jsonPath().param("barName", filter).getList("findAll{it -> it.name == barName}");

        for (HashMap<String, String> map : list) {
            Response detailsResponse = executeRequest(Method.GET, "/breweries/" + map.get("id"));
            detailsResponse.prettyPrint();
        }
    }

    @Test
    @DisplayName("Validacion de los campos de la cerveria con state: California")
    public void test_04_validateBrewery() {
        String filter = "Lagunitas Brewing Co";
        HashMap<String, String> queries = new HashMap<>();
        queries.put("query", "lagunitas");

        Response response = executeRequest(Method.GET, "/breweries/autocomplete", queries);

        List<HashMap<String, String>> list = response.body().jsonPath().param("breweryName", filter).getList("findAll{it -> it.name == breweryName}");

        for (HashMap<String, String> map : list) {
            Response detailsResponse = executeRequest(Method.GET, "/breweries/" + map.get("id"));
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
