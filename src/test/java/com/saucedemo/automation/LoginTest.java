package com.saucedemo.automation;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class LoginTest {

    @Test
    public void loginSuccessful(WebDriver driver) {
        ProductsPage productsPage = new MainPage(driver)
                .login("standard_user", "secret_sauce");
        assertEquals("products", productsPage.getHeader().toLowerCase());
    }

    @Test
    public void loginInvalid(WebDriver driver) {
        String error = new MainPage(driver)
                .loginExpectingError("non-existing", "password");
        assertEquals("Epic sadface: Username and password do not match any user in this service", error);
    }

    @Test
    public void logout(WebDriver driver) {
        ProductsPage productsPage = new MainPage(driver)
                .login("standard_user", "secret_sauce");
        MainPage mainPage = productsPage.logout();
        assertTrue(mainPage.isLoginFormVisible(), "Login button should be visible");
    }
}
