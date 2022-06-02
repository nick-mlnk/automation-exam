package apps.ui.pages;

import apps.ui.components.NavigationHeader;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static infrastructure.configuration.ConfigurationManger.DOMAIN_URL;
import static java.util.Arrays.asList;


@Getter
public class BasePage {

    final protected SelenideElement cart;
    final protected SelenideElement searchField;
    final protected NavigationHeader navHeader;

    protected BasePage() {
        cart = $("a[title='View my shopping cart']");
        searchField = $(".search_query");
        navHeader = new NavigationHeader();
    }

    @Step
    protected void waitUntilLoaded() {
        asList(cart, searchField, navHeader.getElement())
                .forEach(item -> item.shouldBe(Condition.visible));
    }

    @Step
    protected boolean isUserLoggedIn() {
        return navHeader.isUserLoggedIn();
    }

    @Step
    protected void typeProductNameInSearchField(String product) {
        getSearchField().setValue(product);
    }

    @Step
    public ProductPage findFirstProductFromSearch(String product) {
        typeProductNameInSearchField(product);
        $(".ac_results")
                .shouldBe(Condition.visible)
                .$("li").click();
        return new ProductPage();
    }

    @Step
    public CartSummaryPage navigateToCart() {
        cart.click();
        return new CartSummaryPage();
    }

    @Step
    public CartSummaryPage navigateToCartByUrl() {
        Selenide.open(DOMAIN_URL.concat("?controller=order"));
        return new CartSummaryPage();
    }
}
