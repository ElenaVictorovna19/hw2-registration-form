package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormRegistrationTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

    }

    @Test
    void formRegistrationTest() {

        open("/automation-practice-form");

        // Заполнение формы
        $("#firstName").setValue("Grampy");
        $("#lastName").setValue("Cat");
        $("#userEmail").setValue("grampyCat@ya.ru");
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").setValue("1234567890");

        //ДР
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("August");
        $(".react-datepicker__year-select").selectOptionContainingText("2012");
        $(".react-datepicker__day--017").click();

        //Предметы и хобби
        $("#subjectsContainer input").setValue("Math").pressEnter();
        $("#subjectsContainer input").setValue("Economics").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        //загрузка фото
        $("#uploadPicture").uploadFromClasspath("foto/cate.jpeg");

        $("#currentAddress").setValue("currentAddress");

        //Штат и город
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();

        $("#submit").click();

        //Проверка
        $(".modal-content").should(appear);
        $(".table-responsive").shouldHave(text("Grampy Cat"),
                text("grampyCat@ya.ru"),
                text("currentAddress"),
                text("Haryana Karnal"));
    }
}
