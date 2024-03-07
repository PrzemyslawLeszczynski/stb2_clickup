package pl.akademiaqa.url;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ClickUpUrl {

    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAM = "/team";
    private static final String SPACE = "/space";
    private static final String LIST = "/list";
    private static final String TASK = "/task";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getTeams() {
        return TEAM;
    }

    public static String getTeam(String teamId) {
        return TEAM + "/" + teamId;
    }

    public static String getSpaces(String teamId) {
        return getTeam(teamId) + SPACE;
    }

    public static String getSpace(String spaceId) {
        return SPACE + "/" + spaceId;
    }

    public static String getLists(String spaceId) {
        return getSpace(spaceId) + LIST;
    }

    public static String getList(String listId) {
        return LIST + "/" + listId;
    }

    public static String getTasks(String listId) {
        return getList(listId) + TASK;
    }

    public static String getTask(String taskId) {
        return TASK + "/" + taskId;
    }
}
