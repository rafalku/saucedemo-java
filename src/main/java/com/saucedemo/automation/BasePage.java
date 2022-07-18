package com.saucedemo.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private static final int UI_ANIMATION_TIMEOUT = 10;

    protected WebDriver driver;
    protected WebDriverWait uiAnimationWait;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(css = "nav.bm-item-list")
    private WebElement menuItemList;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(css = "#header_container span.title")
    private WebElement headerContainer;

    @FindBy(id = "searchtoggl")
    private WebElement searchToggle;

    @FindBy(id = "s")
    private WebElement searchInput;

    @FindBy(className = "search-btn")
    private WebElement searchButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().window().maximize();
        this.uiAnimationWait = new WebDriverWait(driver, UI_ANIMATION_TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    public String getHeader() {
        return headerContainer.getText();
    }

    public void openMenu() {
        menuButton.click();
        uiAnimationWait.until(ExpectedConditions.visibilityOf(menuItemList));
    }

    public MainPage logout() {
        openMenu();
        logoutLink.click();
        return new MainPage(driver);
    }
}
