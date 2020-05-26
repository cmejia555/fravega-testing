package frontend.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import page.BasePage;

public class BrandsPage extends BasePage {
    private String brandFilter = "//div/label[text()='%s']";
    private final By applyButton = By.id("apply");

    public void selectFilter(String brand) {
        By locator = By.xpath(String.format(brandFilter, brand));
        Assert.assertTrue("No se encontro la marca: " + brand, find(locator));
        click(locator);

        Assert.assertTrue("No se encontro el boton 'APLICAR'", find(applyButton));
        click(applyButton);
    }

}
