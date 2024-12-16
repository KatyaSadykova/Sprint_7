import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Courier;
import org.example.CourierChecks;
import org.example.CourierClient;
import org.junit.After;
import org.junit.Test;

public class CourierLoginTest {


    private final CourierChecks check = new CourierChecks();
    private final CourierClient client = new CourierClient();
    int courierId;

    @After
    public void deleteCourier() {
        if (courierId != 0) {
            ValidatableResponse response = client.deleteCourier(courierId);
            check.deletedSuccesfully(response);
        }
    }


    @Test
    @DisplayName("Вход с правильными учетными данными")
    @Description("Проверка успешного входа курьера с правильными данными")

    public void testCourierLoginSuccess() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        var creds = Courier.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
    }

    @Test
    @DisplayName("Вход с неверным паролем ")
    @Description("Проверка сообщения об ошибке при входе с неверным паролем")
    public void testCourierWrongPassword() {

        var creds = new Courier("test", "12345");
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.notFoundLogin(loginResponse);
    }

    @Test
    @DisplayName("Вход с несуществующим аккаунтом")
    @Description("Проверка сообщения об ошибке при входе с несуществующим аккаунтом")
    public void testCourierLoginAccountNotFound() {
        var creds = new Courier("nonexistentUser", "wrongPassword");
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.notFoundLogin(loginResponse);
    }

    @Test
    @DisplayName("Вход без логина")
    @Description("Проверка сообщения об ошибке при входе без логина")
    public void testCourierLoginNoLogin() {
        var courier = Courier.random();
        var creds = Courier.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.notFoundLogin(loginResponse);
    }

    @Test
    @DisplayName("Вход с паролем без логина")
    @Description("Проверка сообщения об ошибке при входе с паролем без логина")
    public void testCourierWithPasswordNoLogin() {
        var creds = new Courier(" ", "wrongPassword");
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.notFoundLogin(loginResponse);
    }
}