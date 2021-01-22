package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardOrderDeliveryTest {

    DataGenerator dataGenerator = new DataGenerator();

    @BeforeEach
    void openLink(){
        open("http://localhost:9999/");
    }

    @Test
    void shouldAcceptOrder(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(3));
        $("[data-test-id='name'] input").setValue("Семен Семенович");
        $("[data-test-id='phone'] input").setValue("+88005553535");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $(Selectors.withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void emptyCity(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(3));
        $("[data-test-id='name'] input").setValue("Семен Семенович");
        $("[data-test-id='phone'] input").setValue("+88005553535");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void cityNotAvailable(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("ЦУЕФА");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(3));
        $("[data-test-id='name'] input").setValue("Семен Семенович");
        $("[data-test-id='phone'] input").setValue("+88005553535");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void dataNotAvailable(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(0));
        $("[data-test-id='name'] input").setValue("Семен Семенович");
        $("[data-test-id='phone'] input").setValue("+88005553535");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void dataEnteredIncorrectly(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys("20.14.4000");
        $("[data-test-id='name'] input").setValue("Семен Семенович");
        $("[data-test-id='phone'] input").setValue("+88005553535");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(Condition.exactText("Неверно введена дата"));
    }

    @Test
    void nameIsEmpty(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(5));
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+88005553535");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void nameEnteredIncorrectly(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(5));
        $("[data-test-id='name'] input").setValue("Dormamu");
        $("[data-test-id='phone'] input").setValue("+88005553535");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void phoneIsEmty(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(5));
        $("[data-test-id='name'] input").setValue("Семен");
        $("[data-test-id='phone'] input").setValue("");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void phoneEnteredIncorrectly(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(5));
        $("[data-test-id='name'] input").setValue("Семен");
        $("[data-test-id='phone'] input").setValue("+880055535350000");
        $(".checkbox__box").click();
        $(Selectors.byText("Забронировать")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void checkboxIsEmpty(){
        $("[data-test-id='city'] [placeholder='Город']").setValue("Уфа");
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(dataGenerator.calculatedData(5));
        $("[data-test-id='name'] input").setValue("Семен");
        $("[data-test-id='phone'] input").setValue("+88005553535");
        $(Selectors.byText("Забронировать")).click();
        $(".input_invalid[data-test-id='agreement']").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }


}