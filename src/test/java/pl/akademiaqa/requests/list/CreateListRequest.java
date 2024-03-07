package pl.akademiaqa.requests.list;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.url.ClickUpUrl;
import pl.akademiaqa.requests.BaseRequest;

import static io.restassured.RestAssured.given;

public class CreateListRequest {
    public static Response createList(JSONObject list, String spaceId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(list.toString())
                .when()
                .post(ClickUpUrl.getLists(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();

    }
}
