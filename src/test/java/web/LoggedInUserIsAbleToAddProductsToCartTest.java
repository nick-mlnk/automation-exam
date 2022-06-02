package web;

import apps.ui.pages.AccountPage;
import apps.ui.pages.CartSummaryPage;
import apps.ui.pages.ProductPage;
import data.UserData;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LoggedInUserIsAbleToAddProductsToCartTest extends WebTest {

    UserData userData;
    AccountPage accountPage;
    ProductPage productPage;
    String productQueryCriteria;
    String searchedProductName;

    @BeforeClass
    public void setup() {
        userData = UserData.builder().generateFirstModel().build();
        accountPage = landingPage
                .getNavHeader().signIn()
                .goToAuthenticatePage()
                .login(userData);
        productQueryCriteria = "dress";
    }

    static final String STEP_1 = "testUserLoggedInSuccessfully";

    @Test
    public void testUserLoggedInSuccessfully() {
        softAssertions.assertThat(accountPage.isHeadingDisplayed())
                .as("Account Page Heading visibility")
                .isTrue();
        softAssertions.assertThat(accountPage.getPageHeading().getText())
                .as("Heading Text")
                .isEqualToIgnoringCase("my account");
        softAssertions.assertAll();
    }

    static final String STEP_2 = "testProductCanBeFoundBySearch";

    @Test(dependsOnMethods = STEP_1)
    public void testProductCanBeFoundBySearch() {
        productPage = accountPage.findFirstProductFromSearch(productQueryCriteria);
        searchedProductName = productPage.getHeader().getText();

        assertThat(searchedProductName)
                .as("Product Page Header")
                .containsIgnoringCase(productQueryCriteria);
    }

    @Test(dependsOnMethods = STEP_2)
    public void testProductIsAddedToCart() {
        CartSummaryPage cartPage = productPage.addToCartWithClosingOverlay()
                .navigateToCart();

        assertThat(cartPage.isProductPresent(searchedProductName))
                .as("Presence of Product in Cart")
                .isTrue();
    }

    @AfterClass(alwaysRun = true)
    public void clearCart() {
        CartSummaryPage cartPage = productPage.navigateToCartByUrl();
        if (!cartPage.isCartEmpty()) {
            cartPage.clearCart();
        }
    }
}
