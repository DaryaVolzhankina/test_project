package steps;

import config.BaseConfig;
import enums.Codes;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.Helpers.*;

public class WeatherStackSteps {

    /**
     * Экземпляр интерфейса с конфигурацией
     */
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Экземпляр класса с оболочкой ответа
     */
    private Response response;

    /**
     * Экземпляр класса RequestSpecification
     */
    private RequestSpecification requestSpecification;

    @ParameterType(".*")
    public Codes code(String code) {
        return Codes.valueOf(code);
    }

    @Given("Get requestSpec")
    public void getRequestSpec() {
        RestAssured.baseURI = config.baseUrl();
        requestSpecification = RestAssured
                .given()
                .contentType("application/json")
                .accept("*/*");
    }

    @When("Send a request to the endpoint \"current\" with parameters {string}")
    public void sendRequestToEndpointCurrentWithQueryParameter(String query) throws URISyntaxException {
        response = requestSpecification.when().get(new URI("current?access_key=" + config.accessKey() + "&query=" + query));
    }

    @When("Send a request to the endpoint \"current\" with parameters {string} and {string}")
    public void sendRequestToEndpointCurrentWithQueryUnitParameters(String query, String unit) throws URISyntaxException {
        response = requestSpecification.when().get(new URI("current?access_key=" + config.accessKey() + "&query=" + query + "&units=" + unit));
    }

    @When("Send a request to the endpoint \"current\" with parameters {string}, {string} and {string}")
    public void sendRequestToEndpointCurrentWithAccessQueryUnitParameters(String accessKey, String query, String unit) throws URISyntaxException {
        response = requestSpecification.when().get(new URI("current?access_key=" + accessKey + "&query=" + query + "&units=" + unit));
    }


    @Then("Verify parameters {string},{string},{string},{string}")
    public void verifyParameters(String language, String name, String country, String utcOffset) throws ParseException {

        response = response.then().statusCode(200).extract().response();

        //Парсим ответ API в json
        JSONObject fileObject = new JSONObject(response.asPrettyString());

        //Достаем из json нужные объекты
        JSONObject request = fileObject.getJSONObject("request");
        JSONObject location = fileObject.getJSONObject("location");
        JSONObject current = fileObject.getJSONObject("current");
        JSONArray weatherIcons = current.getJSONArray("weather_icons");

        //Текущая дата, переводим в формат год-месяц-день
        Date timePoint = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date localtime = dateFormat.parse(location.getString("localtime"));

        //Проверяем ответ API с ожидаемыми
        Assert.assertEquals(request.getString("type"), "City");
        Assert.assertEquals(request.getString("language"), language);

        Assert.assertEquals(location.getString("name"), name);
        Assert.assertEquals(location.getString("country"), country);
        Assert.assertEquals(location.getString("utc_offset"), utcOffset);
        Assert.assertEquals(dateFormat.format(localtime), dateFormat.format(timePoint));

        checkIntParams(10, 30, current.getInt("temperature"), "Temperature");
        checkArray(weatherIcons, "weather_icons");
        checkIsDay(current.getString("is_day"));
    }

    @Then("Check error response body {code},{string},{string}")
    public void checkErrorResponseBodyParameters(Codes code, String type, String info) {
        response = response.then().statusCode(200).extract().response();

        //Парсим ответ API в json
        JSONObject fileObject = new JSONObject(response.asPrettyString());

        //Достаем из json нужные объекты
        JSONObject error = fileObject.getJSONObject("error");
        Boolean success = fileObject.getBoolean("success");

        Assert.assertEquals(success, false);

        Assert.assertEquals(error.getInt("code"), code.getValue());
        Assert.assertEquals(error.getString("type"), type);
        Assert.assertEquals(error.getString("info"), info);
    }
}
