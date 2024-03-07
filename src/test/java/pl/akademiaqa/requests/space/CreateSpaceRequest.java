package pl.akademiaqa.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.properties.ClickUpProperties;
import pl.akademiaqa.requests.BaseRequest;

import static io.restassured.RestAssured.given;
import static pl.akademiaqa.url.ClickUpUrl.getSpaces;

public class CreateSpaceRequest {

    public static Response createSpace(JSONObject space) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(space.toString())
                .when()
                .post(getSpaces(ClickUpProperties.getTeamId()))
                .then()
                .log().ifError()
                .extract()
                .response();

    }
}
