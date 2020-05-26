import org.junit.Test;
import pages.HomePage;
import pages.ProductsPage;


public class FravegaTest extends TestBase {


    @Test
    public void test_01_searchSomething() {
        String product = "Heladera";
        HomePage homePage = new HomePage();
        homePage.searchProduct(product);
        ProductsPage productsPage = new ProductsPage();
        productsPage.validateSearchedProduct(product);
    }

    @Test
    public void test_02_filterProduct() {
        String product = "Heladera";
        String categoryFilter = "Heladeras, Freezers y Cavas";
        HomePage homePage = new HomePage();
        homePage.searchProduct(product);
        ProductsPage productsPage = new ProductsPage();
        productsPage.validateSearchedProduct(product);
        productsPage.filterByCategory(categoryFilter);
    }

}
