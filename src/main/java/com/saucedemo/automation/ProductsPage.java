package com.saucedemo.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(css = "select[data-test='product_sort_container']")
    private WebElement sortSelect;

    @FindBy(css = "span.active_option")
    private WebElement activeOption;

    @FindBy(css = "div.inventory_item_price")
    private List<WebElement> prices;

    @FindBy(css = "a.shopping_cart_link")
    private WebElement shoppingCartLink;


    public ProductsPage(WebDriver driver, boolean shouldOpen) {
        super(driver);
        if (shouldOpen) {
            driver.get("https://www.saucedemo.com/inventory.html");
        }
    }

    public ProductsPage(WebDriver driver) {
        this(driver, false);
    }

    public ShoppingCartPage goToShoppingCart() {
        shoppingCartLink.click();
        return new ShoppingCartPage(driver);
    }

    public void sort(SortingOrder order) {
        new Select(sortSelect).selectByValue(order.getValue());
        uiAnimationWait.until(
                ExpectedConditions.textToBePresentInElement(activeOption, order.getText().toUpperCase())
        );
    }

    public double[] getPrices() {
        return prices.stream().mapToDouble(
                price -> Double.parseDouble(price.getText().substring(1))
        ).toArray();
    }

    public void addToCart(String item) {
        driver.findElement(By.id("add-to-cart-" + item.toLowerCase().replace(" ", "-")))
                .click();
    }

}
