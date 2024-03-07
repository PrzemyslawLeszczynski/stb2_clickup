package pl.akademiaqa.tests.e2e;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.akademiaqa.dto.task.request.CreateTaskRequestDto;
import pl.akademiaqa.requests.list.CreateListRequest;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.task.CreateTaskRequest;
import pl.akademiaqa.requests.task.UpdateTaskRequest;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateTaskE2ETest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static String SPACE_NAME = "E2E SPACE";
    private static String LIST_NAME = "ZADANIA DO E2E";
    private static String TASK_NAME = "Przetestowac clickup";
    private String spaceId;
    private static String listId;
    private String taskId;

    @Test
    void updateTaskTest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space create with id: {}", spaceId);

        listId = createListStep();
        LOGGER.info("List create with id: {}", listId);

        taskId = createTaskStep();
        LOGGER.info("Task create with id: {}", taskId);

        updateTaskStep();
        closeTaskStep();
        deleteSpaceStep();
    }

    private String createSpaceStep() {
        JSONObject json = new JSONObject();
        json.put("name", SPACE_NAME);

        final var response = CreateSpaceRequest.createSpace(json);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo(SPACE_NAME);

        return response.jsonPath().getString("id");
    }

    private String createListStep() {
        JSONObject json = new JSONObject();
        json.put("name", LIST_NAME);

        final var response = CreateListRequest.createList(json, spaceId);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo(LIST_NAME);

        return response.jsonPath().getString("id");
    }

    private static String createTaskStep() {

        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(TASK_NAME);
        taskDto.setDescription("Ciekawe jak to działa");
        taskDto.setStatus("to do");

        final var response = CreateTaskRequest.createTask(taskDto, listId);
//        LOGGER.info("CREATE TASK RESPONSE OBJ: {}", response);

        assertThat(response.getName()).isEqualTo(TASK_NAME);
        assertThat(response.getDescription()).isEqualTo("Ciekawe jak to działa");

        return response.getId();
    }

    private void updateTaskStep() {
        JSONObject updateTask = new JSONObject();
        updateTask.put("name", "Zmieniam nazwę zadania");
        updateTask.put("description", "Zmieniam opis zadania");

        final var response = UpdateTaskRequest.updateTask(updateTask, taskId);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo("Zmieniam nazwę zadania");
        assertThat(response.jsonPath().getString("description")).isEqualTo("Zmieniam opis zadania");

    }

    private void closeTaskStep() {
        JSONObject closeTask = new JSONObject();
        closeTask.put("status", "complete");

        final var response = UpdateTaskRequest.updateTask(closeTask, taskId);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("status.status")).isEqualTo("complete");
    }

    private void deleteSpaceStep() {

        final var response = DeleteSpaceRequest.deleteSpace(spaceId);
        assertThat(response.statusCode()).isEqualTo(200);

    }
}
