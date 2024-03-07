package pl.akademiaqa.tests.space;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;

import static org.assertj.core.api.Assertions.assertThat;


class CreateSpaceTest {
    private final static String SPACE_NAME = "SPACE NAME FROM JAVA";
    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final var response = CreateSpaceRequest.createSpace(space);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo(SPACE_NAME);

        final var spaceId = response.jsonPath().getString("id");
        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);

        assertThat(deleteResponse.statusCode()).isEqualTo(200);

    }
}
