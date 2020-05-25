package pages;

import org.junit.Assert;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By searchInput = By.xpath("//form/input[@placeholder='Buscar productos']");
    private final By linkToMyAccount = By.xpath("//div/a/span[text()='Mi cuenta']");
    private final By searchButton = By.xpath("//form/button[@type='submit']");

    public HomePage() {
        validatePage();
    }

    private void validatePage() {
        Assert.assertTrue("No se encontro el campo de 'Buscar productos'", exists(searchInput));
        Assert.assertTrue("No se encontro la opcion para ingresar a 'Mi cuenta'", exists(linkToMyAccount));
    }

    public void searchProduct(String product) {
        Assert.assertTrue("No se encontro el campo de 'Buscar productos'", exists(searchInput));
        sendKeys(searchInput, product);

        Assert.assertTrue("No se encontro el boton de busqueda", exists(searchButton));
        click(searchButton);
    }
}
