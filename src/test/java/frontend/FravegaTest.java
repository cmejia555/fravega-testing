package frontend;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import frontend.pages.HomePage;
import frontend.pages.ProductsPage;


@DisplayName("Fravega Test")
@Epic("Frontend")
public class FravegaTest extends BaseTest {

    @Test
    @DisplayName("Buscar producto")
    @Description("Se verifica que en el menu lateral aparezca como titulo el producto buscado")
    public void test_01_searchSomething() {
        String product = "Heladera";

        HomePage homePage = new HomePage();
        homePage.searchProduct(product);

        ProductsPage productsPage = new ProductsPage();
        productsPage.validateSearchedProduct(product);
    }

    @Test
    @DisplayName("Agregar filtros y validar")
    @Description("Se verifica los items de la grilla de resultados y los filtros aplicados")
    public void test_02_filterProduct() {
        String product = "Heladera";
        String categoryFilter = "Heladeras, Freezers y Cavas";
        String brandFilter = "Samsung";
        int itemsByPage = 8;

        HomePage homePage = new HomePage();
        homePage.searchProduct(product);

        ProductsPage productsPage = new ProductsPage();
        productsPage.validateSearchedProduct(product);
        productsPage.filterByCategory(categoryFilter);
        productsPage.filterByBrand(brandFilter);
        productsPage.validateItemsGrid(itemsByPage, brandFilter);
        productsPage.validateAppliedCategoryFilter(categoryFilter);
    }

}
