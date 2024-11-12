package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PymentElement {
    private SelenideElement buyButton = $(byText("Купить"));
    private SelenideElement buyOnCredit = $(byText("Купить в кредит"));
    private SelenideElement continueButton = $(byText("Продолжить"));

    private SelenideElement nomberCard = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $("[placeholder='08']");
    private SelenideElement year = $("[placeholder='22']");
    private SelenideElement owner = $$("[class=input__inner]").findBy(text("Владелец")).$("[class=input__control]");
    private SelenideElement cvvCode = $("[placeholder='999']");

    private SelenideElement incorretcFormat =$(withText("Неверный формат"));

    private SelenideElement yearIncorrect = $$("[class=input__inner]").findBy(text("Год"))
            .$(withText("Неверный формат"));
    private SelenideElement nameIncorrect = $(withText("Поле обязательно для заполнения"));
    private SelenideElement yearLessToday = $(withText("Истёк срок действия карты"));

    private SelenideElement completedPay = $(byText("Операция одобрена Банком."));
    private SelenideElement operationFailure = $(byText("Ошибка! Банк отказал в проведении операции."));


    public void buttonVisible(){
        buyButton.shouldBe(visible);
        buyOnCredit.shouldBe(visible);
    }
    public void elementsVisible(){
        nomberCard.shouldBe(visible);
        month.shouldBe(visible);
        year.shouldBe(visible);
        owner.shouldBe(visible);
        cvvCode.shouldBe(visible);
        continueButton.shouldBe(visible);


    }

    public void comledetPayVisible(){
        completedPay.shouldBe(visible, Duration.ofSeconds(15));
    }
    public void failPayVisible(){
        operationFailure.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void clickBuyButton(){
        buyButton.click();
    }
    public void clickBuyOnCreditButton(){
        buyOnCredit.click();
    }

    public void getAppruveNomberq(DataHelper.cardInfo cardInfo) {
        nomberCard.setValue(cardInfo.getCardNubmer());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYars());
        owner.setValue(cardInfo.getNameOwner());
        cvvCode.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void invalidFormat(){
        incorretcFormat.shouldBe(visible);
    }
    public void emptyOwner(){
        nameIncorrect.shouldBe(visible);
    }
    public void yearLessToday(){
        yearLessToday.shouldBe(visible);
    }

}
