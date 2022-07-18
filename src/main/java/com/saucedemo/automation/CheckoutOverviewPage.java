package com.saucedemo.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CheckoutOverviewPage extends BasePage {

    @FindBy(css = "div.summary_total_label")
    private WebElement totalAmount;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public double getTotalAmount() {
        String total = totalAmount.getText();
        return Double.parseDouble(total.substring(total.indexOf("$") + 1));
    }

    public CheckoutCompletePage finish() {
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }
}
