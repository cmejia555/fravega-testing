package frontend;

import org.junit.Test;
import frontend.pages.HomePage;
import frontend.pages.ProductsPage;


public class FravegaTest extends BaseTest {

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
        String brandFilter = "Whirlpool";
        int itemsByPage = 15;

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
