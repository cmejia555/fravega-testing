package pages;

import org.junit.Assert;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {
    private final By sideBar = By.name("aggregations");
    private final By itemGrid = By.name("itemsGrid");
    private final By sideBarTitle = By.name("categoryTitle");
    private String categoryTitleTemplate = "//li/a/h3[text()='%s']";

    public ProductsPage() {
        validatePage();
    }

    private void validatePage() {
        Assert.assertTrue("No se encontro el menu lateral", exists(sideBar));
        Assert.assertTrue("No se encontro la grilla de items con los productos", exists(itemGrid));
    }

    public void validateSearchedProduct(String product) {
        Assert.assertEquals("El menu lateral deberia tener como titulo: " + product, product, getText(sideBarTitle));
    }

    public void filterByCategory(String category) {
        By locator = By.xpath(String.format(categoryTitleTemplate, category));
        Assert.assertTrue("No se encontro la categoria: " + category, exists(locator));
        click(locator);
    }
}
