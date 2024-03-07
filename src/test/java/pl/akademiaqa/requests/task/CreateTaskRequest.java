package pl.akademiaqa.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.dto.task.request.CreateTaskRequestDto;
import pl.akademiaqa.dto.task.response.CreateTaskResponseDto;
import pl.akademiaqa.url.ClickUpUrl;
import pl.akademiaqa.requests.BaseRequest;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {
    public static Response createTask(JSONObject task, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .when()
                .post(ClickUpUrl.getTasks(listId))
                .then()
                .log().ifError()
                .extract()
                .response();

    }

    public static CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDto)
                .when()
                .post(ClickUpUrl.getTasks(listId))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response()
                .as(CreateTaskResponseDto.class);

    }
}
