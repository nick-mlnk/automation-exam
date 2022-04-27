package web;

import apps.web.ui.components.CartOverlay;
import apps.web.ui.pages.CartSummaryPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.String.format;

public class CartTest extends WebTest {

    final static String SUCCESS_MSG = "Product successfully added to your shopping cart";
    CartOverlay cartOverlay;
    CartSummaryPage cartSummaryPage;

    @DataProvider
    public static Object[][] productDataProvider() {
        return new Object[][]{
                {"Blouse", false},
                {"Printed Summer Dress", true}
        };
    }


    @Test(dataProvider = "productDataProvider", groups = "itemsAddToCart")
    public void testItemsAreAddedToCartSuccessfully(String title, boolean hasDiscount) {
        cartOverlay = landingPage.addProductToCart(title, hasDiscount);
        String actualHeaderText = cartOverlay.getHeaderText();

        Assertions.assertThat(actualHeaderText)
                .isEqualTo(SUCCESS_MSG)
                .as(format("Success message [%s] should be shown on cart overlay", SUCCESS_MSG));
    }

    @Test(dependsOnMethods = "testItemsAreAddedToCartSuccessfully")
    public void testItemsAreRemovedFromCartSuccessfully() {
        cartSummaryPage = landingPage.navigateToCartPage()
                .clearCart();

        Assertions.assertThat(cartSummaryPage.isCartEmpty())
                .isTrue()
                .as("Cart should be empty");
    }


    @AfterMethod(onlyForGroups = "itemsAddToCart")
    public void closeOverlay() {
        cartOverlay.closeOverlay();
    }
}
