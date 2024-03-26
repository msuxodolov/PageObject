package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");

    // private final SelenideElement toInput = $("[data-test-id='to'] input");
    private final SelenideElement actionTransfer = $("[data-test-id='action-transfer']");
    // private final SelenideElement actionalCancel = $("[data-test-id='action-cancel']");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    private final SelenideElement headerText = $(byText("Пополнение карты"));

    public TransferPage() {

        headerText.shouldBe(visible);
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        //    toInput.setValue("");
        actionTransfer.click();
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    //  public void errorFindMessage(String expectedText) {
    //      errorNotification.shouldBe(visible);
    //  }

    public void errorFindMessage(String expectedText) {
        errorNotification.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

}
