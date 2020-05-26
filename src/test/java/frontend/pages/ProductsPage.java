package frontend.pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.BasePage;

import java.util.List;


public class ProductsPage extends BasePage {
    private final By sideBarSection = By.name("aggregations");
    private final By itemsGridSection = By.name("itemsGrid");
    private final By sideBarTitle = By.name("categoryTitle");
    private String categoryTitleTemplate = "//li/a/h3[text()='%s']";
    private String brandCheckBoxTemplate = "//li[@name='brandAggregation']/a/label[text()='%s']";
    private By items = By.xpath("//ul[@name='itemsGrid']/li");
    private By breadcrumb = By.xpath("//div[@name='breadcrumb']/ul/li/a[@cursor='default']");
    private By showAllBrands = By.xpath("//ul/a[text()='Ver todas']");

    public ProductsPage() {
        validatePage();
    }

    private void validatePage() {
        Assert.assertTrue("No se encontro el menu lateral", find(sideBarSection));
        Assert.assertTrue("No se encontro la grilla de items con los productos", find(itemsGridSection));
    }

    public void validateSearchedProduct(String product) {
        Assert.assertTrue("No se encontro el titulo del menu lateral", find(sideBarTitle));
        Assert.assertEquals("El menu lateral deberia tener como titulo: " + product, product, getText(sideBarTitle));
    }

    public void filterByCategory(String category) {
        By locator = By.xpath(String.format(categoryTitleTemplate, category));
        Assert.assertTrue("No se encontro la categoria: " + category, find(locator));
        click(locator);
    }

    public void filterByBrand(String brand) {
        By locator = By.xpath(String.format(brandCheckBoxTemplate, brand));
        if (find(locator)) {
            click(locator);
        } else {
            Assert.assertTrue("No se encontro el link a 'Ver todas'", find(showAllBrands));
            click(showAllBrands);
            new BrandsPage().selectFilter(brand);
        }
    }

    public void validateItemsGrid(int gridSize, String itemTitle) {
        Assert.assertTrue("No se encontraron items en la grilla", find(items));
        List<WebElement> itemList = findElements(items);

        Assert.assertEquals("El tamaño de la grilla no coincide con lo que se muestra en pantalla",
                gridSize, itemList.size());

        for (WebElement item: itemList) {
            String text = item.findElement(By.xpath(".//article/div/h4")).getText();
            Assert.assertThat("El item no contiene el titulo: " + itemTitle, text, Matchers.containsString(itemTitle));
        }
    }

    public void validateAppliedCategoryFilter(String filter) {
        Assert.assertTrue("No se encontro el breadcrumb", find(breadcrumb));
        Assert.assertEquals("En el breadcrumb de la pagina deberia visualizarse: " + filter,
                filter, getText(breadcrumb));
    }
}
