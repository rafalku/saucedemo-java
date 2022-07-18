package com.saucedemo.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(css = "div.inventory_item_name")
    private List<WebElement> items;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public String[] getItems() {
        return items.stream().map(WebElement::getText).toArray(String[]::new);
    }

    public CheckoutPage goToCheckout() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
