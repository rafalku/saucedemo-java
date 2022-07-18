package com.saucedemo.automation;


import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(SeleniumJupiter.class)
public class ShoppingTest {

    private ProductsPage productsPage;

    @BeforeEach
    void loginFixture(WebDriver driver) {
        new MainPage(driver);
        driver.manage().addCookie(new Cookie("session-username", "standard_user"));
        productsPage = new ProductsPage(driver, true);
    }


    @Test
    public void sortByPrice() {
        productsPage.sort(SortingOrder.PRICE_ASC);
        double[] prices = productsPage.getPrices();
        double[] sortedPrices = Arrays.copyOf(prices, prices.length);
        Arrays.sort(sortedPrices);
        assertArrayEquals(sortedPrices, prices);
    }


    private static Stream<Arguments> shoppingListProvider() {
        return Stream.of(
                arguments((Object) new String[]{
                        "Sauce Labs Bike Light",
                        "Sauce Labs Backpack",
                        "Sauce Labs Fleece Jacket"
                }),
                arguments((Object) new String[]{
                        "Sauce Labs Onesie"
                })
        );
    }

    @ParameterizedTest
    @MethodSource("shoppingListProvider")
    public void addItemsToCart(String[] shoppingList) {
        for(String item: shoppingList) {
            productsPage.addToCart(item);
        }
        ShoppingCartPage shoppingCartPage = productsPage.goToShoppingCart();
        assertThat(Arrays.asList(shoppingCartPage.getItems()), Matchers.containsInAnyOrder(shoppingList));
    }

    @Test
    public void completePurchase() {
        productsPage.addToCart("Sauce Labs Bike Light");
        CheckoutPage checkoutPage = productsPage.goToShoppingCart().goToCheckout();
        checkoutPage.fillInForm("John", "Doe", "123456");
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.continuePurchase();
        assertEquals(10.79, checkoutOverviewPage.getTotalAmount());
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finish();
        assertEquals("thank you for your order",
                checkoutCompletePage.getCompleteMessage().toLowerCase());
    }
}
