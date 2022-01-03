package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDelivery {

    public static String genDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void shouldSetMeetingDate() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Иваново");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        //$("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        String date = genDate(5);
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("Иванова-Иванова Ива");
        $("[name='phone']").setValue("+70000000000");
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id='notification']").shouldHave(Condition.text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15));
    }
}