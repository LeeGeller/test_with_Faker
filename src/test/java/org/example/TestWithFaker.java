package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;


class TestWithFaker {

    @Test
    void testDeliveryCard() {

        DataGenerate.UserInfo validUser = DataGenerate.Registration.generateUser("ru");
        int firstDays = 4;
        String firstMeeting = DataGenerate.date(firstDays);
        int secondDays = 5;
        String secondMeeting = DataGenerate.date(secondDays);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[type='text']").setValue(validUser.getCity());
        $("[data-test-id='date']").click();
        $x("//*[@data-test-id='date']//input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input")
                .sendKeys(firstMeeting);
        $x("//*[@data-test-id='name']//input")
                .sendKeys(validUser.getName());
        $x("//*[@data-test-id='phone']//input")
                .sendKeys(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("[class='button__content']").click();
        $("[data-test-id='date']").click();
        $x("//*[@data-test-id='date']//input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input").sendKeys(secondMeeting);
        $("[class='button__content']").click();
        $x("//*[@class='notification__content']//button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeeting)).shouldBe(Condition.visible);
    }

}