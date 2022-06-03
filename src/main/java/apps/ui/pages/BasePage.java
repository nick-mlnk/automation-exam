package apps.ui.pages;

import apps.ui.components.NavigationHeader;
import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;

import static infrastructure.configuration.ConfigurationManger.DOMAIN_URL;
import static java.util.Arrays.asList;


@Getter
public class BasePage {

    final protected SelenideElement cart;
    final protected SelenideElement searchField;
    final protected NavigationHeader navHeader;
    final protected SelenideDriver driver;


    protected BasePage(SelenideDriver driver) {
        this.driver = driver;
        cart = this.driver.$("a[title='View my shopping cart']");
        searchField = this.driver.$(".search_query");
        navHeader = new NavigationHeader(this.driver);
    }

    @Step
    protected void waitUntilLoaded() {
        asList(cart, searchField, navHeader.getElement())
                .forEach(item -> item.shouldBe(Condition.visible, Duration.ofSeconds(8)));
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
        driver.$(".ac_results")
                .shouldBe(Condition.visible, Duration.ofSeconds(8))
                .$("li").click();
        return new ProductPage(driver);
    }

    @Step
    public CartSummaryPage navigateToCart() {
        cart.click();
        return new CartSummaryPage(driver);
    }

    @Step
    public CartSummaryPage navigateToCartByUrl() {
        driver.open(DOMAIN_URL.concat("?controller=order"));
        return new CartSummaryPage(driver);
    }
}
