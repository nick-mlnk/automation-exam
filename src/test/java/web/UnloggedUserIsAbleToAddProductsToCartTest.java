package web;

import apps.ui.pages.CartSummaryPage;
import data.UserData;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnloggedUserIsAbleToAddProductsToCartTest extends WebTest {

    UserData userData;
    CartSummaryPage cartPage;
    String searchedProductName;

    @BeforeClass
    public void setup() {
        userData = UserData.builder().generateSecondModel().build();
        searchedProductName = landingPage.addRandomProductToCartWithClosingOverlay();
        cartPage = landingPage
                .getNavHeader().signIn()
                .goToAuthenticatePage()
                .login(userData)
                .navigateToCartByUrl();
    }

    @Test
    public void testProductIsAddedToCart() {
        assertThat(cartPage.isProductPresent(searchedProductName))
                .as("Presence of Product in Cart")
                .isTrue();
    }

    @AfterClass(alwaysRun = true)
    public void clearCart() {
        cartPage = landingPage.navigateToCartByUrl();
        if (!cartPage.isCartEmpty()) {
            cartPage.clearCart();
        }
    }
}
