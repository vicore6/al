package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    @Test
    void shouldBookingACard() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='city']  input").setValue("Москва");
//        String text = $("[formnovalidate][view]").getAttribute("value");
        String planningDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date']  input").setValue(planningDate);
        $("[data-test-id='name']  input").setValue("Феодосеев Виктор");
        $("[data-test-id='phone']  input").setValue("+79111111111");
        $("[data-test-id='agreement']").click();
        $(".grid-col button[role='button']").click();


        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
//        или
//        Duration.ofSeconds(15);
//        Assertions.assertTrue($(withText("Успешно!")).exists());

        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
//        Через ассерты:
//        Assertions.assertTrue($(withText("Встреча успешно забронирована на")).exists());
//        Assertions.assertTrue($(By.cssSelector(".notification__content")).shouldHave(Condition.text(planningDate)).exists());
    }

}