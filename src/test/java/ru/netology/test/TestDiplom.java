package ru.netology.test;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.SQLHelper;
import ru.netology.page.PymentElement;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.cleanTable;

public class TestDiplom {
    PymentElement element = new PymentElement();

    @AfterAll
    static void teardown() {
        cleanTable();
    }

    @BeforeEach
    void setUp() {
        var pymentElement = open("http://localhost:8080/", PymentElement.class);
    }

    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }


    @Test
    @DisplayName("Отображение элементов, приобретение билета в кредит со статусом DECLINED отображение в БД)")
    void visibleElementsAndBuyOnCreditDec() {
        element.clickBuyOnCreditButton();
        element.buttonVisible();
        element.elementsVisible();
        element.getAppruveNomberq(getNoValidCard());
        element.comledetPayVisible();
        int actual = SQLHelper.getCreditStatus();
        int exp = 0; // значение = 0 означает статус DECLINED
        Assertions.assertEquals(exp, actual);


    }

    @Test
    @DisplayName("Приобретение билета в кредит со статусом APPROVED и отображение в БД)")
    void byOnCreditApp() {
        element.clickBuyOnCreditButton();
        element.getAppruveNomberq(getValidCard());
        element.comledetPayVisible();
        int actual = SQLHelper.getCreditStatus();
        int exp = 1; // значение = 1 означает статус APPROVED
        Assertions.assertEquals(exp, actual);
    }

    @Test
    @DisplayName("Отображение элементов, приобретение билета покупкой со статусом APPROVED и отображение в БД")
    void visibleElementsAndBuyApp() {
        element.clickBuyButton();
        element.buttonVisible();
        element.elementsVisible();
        element.getAppruveNomberq(getValidCard());
        element.comledetPayVisible();
        int actual = SQLHelper.getBuyStatus();
        int exp = 1;
        System.out.println(SQLHelper.getBuyStatus());
        Assertions.assertEquals(exp, actual);
    }

    @Test
    @DisplayName("Приобретение билета покупкой со статусом DECLINED отображение статуса в БД")
    void visibleElementsAndBuyDec() {
        element.clickBuyButton();
        element.getAppruveNomberq(getNoValidCard());
        element.comledetPayVisible();
        int actual = SQLHelper.getBuyStatus();
        int exp = 0;
        System.out.println(SQLHelper.getBuyStatus());
        Assertions.assertEquals(exp, actual);
    }
    @Test
    @DisplayName("Отказ операции при покупке в кредит")
    void visibleFailPayOnCredit(){
        element.clickBuyOnCreditButton();
        element.buttonVisible();
        element.elementsVisible();
        element.getAppruveNomberq(getNoValidCard());
        element.failPayVisible();
    }

    @Test
    @DisplayName("Отказ операции при покупке ")
    void visibleFailPay(){
        element.clickBuyButton();
        element.buttonVisible();
        element.elementsVisible();
        element.getAppruveNomberq(getNoValidCard());
        element.failPayVisible();
    }

    @Test
    @DisplayName("Пустое поле Номер карты")
    void emptyNomberCard() {
        element.clickBuyButton();
        element.getAppruveNomberq(getEmptyNumberCard());
        element.invalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Месяц")
    void emptyMonth() {
        element.clickBuyButton();
        element.getAppruveNomberq(getEmptyMonth());
        element.invalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Год")
    void emptyYear() {
        element.clickBuyButton();
        element.getAppruveNomberq(getEmptyYear());
        element.invalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Владелец")
    void emptyOwner() {
        element.clickBuyButton();
        element.getAppruveNomberq(getEmptyName());
        element.emptyOwner();
    }

    @Test
    @DisplayName("Пустое поле CVV")
    void emptyCVVCode() {
        element.clickBuyButton();
        element.getAppruveNomberq(getEmptyCVC());
        element.invalidFormat();
    }

    @Test
    @DisplayName("Нулевой месяц")
    void zeroMonth() {
        element.clickBuyButton();
        element.getAppruveNomberq(getZeroMonth());
        element.invalidFormat();
    }

    @Test
    @DisplayName("Месяц меньше Today")
    void monthMinToday() {
        element.clickBuyButton();
        element.getAppruveNomberq(getYearLessToday());
        element.yearLessToday();
    }


}