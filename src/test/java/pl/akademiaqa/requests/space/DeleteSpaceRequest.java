package pl.akademiaqa.requests.space;

import io.restassured.response.Response;
import pl.akademiaqa.requests.BaseRequest;

import static io.restassured.RestAssured.given;
import static pl.akademiaqa.url.ClickUpUrl.getSpace;

public class DeleteSpaceRequest {
    public static Response deleteSpace(String spaceId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .when()
                .delete(getSpace(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();

    }
}
