package com.sandbox.auto.api03;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class TrelloApiTests {
    private RequestSpecification reqSpecification;
    private Board board;
    private Card card;
    private static final String BOARD_PATH = "boards";
    private static final String CARD_PATH = "cards";
    private static final String LIST_PATH = "lists";
    String boardName = "Test Board";
    String cardName = "First card";
    String boardBackground = "pink";
    String cardCoverColor = "green";
    String cardDescription = "My card description";
    String toDoListName = "To Do";

    @BeforeClass
    public void setup() {

        PropertiesService propertiesService = new PropertiesService();
        Properties properties = propertiesService.getProperties();
        SpecificationsService specification = new SpecificationsService(properties);
        reqSpecification = specification.requestSpec();
        createBoard();
        createCard();
    }

    public void createBoard() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", boardName);

        board = RestAssured.given()
                .spec(reqSpecification)
                .queryParams(parameters)
                .when()
                .post(BOARD_PATH)
                .then()
                .extract()
                .response()
                .as(Board.class);
    }

    public String getToDoListId() {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("boardPath", BOARD_PATH);
        pathParameters.put("boardId", board.getBoardId());
        pathParameters.put("listPath", LIST_PATH);

        String toDolistId = RestAssured.given()
                .spec(reqSpecification)
                .pathParams(pathParameters)
                .when()
                .get("{boardPath}/{boardId}/{listPath}")
                .then()
                .extract()
                .response()
                .path("find { it.name == '" + toDoListName + "' }.id");
        return toDolistId;
    }

    public void createCard() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", cardName);
        parameters.put("idList", getToDoListId());

        card = RestAssured.given()
                .spec(reqSpecification)
                .queryParams(parameters)
                .pathParam("cardPath", CARD_PATH)
                .when()
                .post("{cardPath}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.equalTo(cardName))
                .extract()
                .response()
                .as(Card.class);

        assertThat(card.getCardName()).isEqualTo(cardName);
    }

    @Test(description = "Change board's background color")
    public void updateBoardColorTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("prefs/background", boardBackground);

        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("boardPath", BOARD_PATH);
        pathParameters.put("boardId", board.getBoardId());

        RestAssured.given()
                .spec(reqSpecification)
                .queryParams(parameters)
                .pathParams(pathParameters)
                .pathParam("boardId", board.getBoardId())
                .when()
                .put("{boardPath}/{boardId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("prefs.background", Matchers.equalTo(boardBackground));
    }

    @Test(description = "Get a card by Id")
    public void getCardById() {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("cardPath", CARD_PATH);
        pathParameters.put("cardId", card.getCardId());

        RestAssured.given()
                .spec(reqSpecification)
                .pathParams(pathParameters)
                .when()
                .get("{cardPath}/{cardId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", Matchers.equalTo(card.getCardId()))
                .body("name", Matchers.equalTo(card.getCardName()));
    }

    @Test(description = "Get all cards on a board")
    public void getBoardCards() {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("boardPath", BOARD_PATH);
        pathParameters.put("boardId", board.getBoardId());
        pathParameters.put("cardPath", CARD_PATH);

        RestAssured.given()
                .spec(reqSpecification)
                .pathParams(pathParameters)
                .when()
                .get("{boardPath}/{boardId}/{cardPath}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo(card.getCardName()));
    }

    @Test(description = "Change card's background color")
    public void updateCardCoverColorTest() {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> cover = new HashMap<>();
        cover.put("color", cardCoverColor);
        requestBody.put("cover", cover);

        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("cardPath", CARD_PATH);
        pathParameters.put("cardId", card.getCardId());

        RestAssured.given()
                .spec(reqSpecification)
                .body(requestBody)
                .pathParams(pathParameters)
                .when()
                .put("{cardPath}/{cardId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("cover.color", Matchers.equalTo(cardCoverColor));
    }

    @Test(description = "Change card's description")
    public void updateCardDescriptionTest() {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("cardPath", CARD_PATH);
        pathParameters.put("cardId", card.getCardId());

        RestAssured.given()
                .spec(reqSpecification)
                .queryParam("desc", cardDescription)
                .pathParams(pathParameters)
                .when()
                .put("{cardPath}/{cardId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("desc", Matchers.equalTo(cardDescription));
    }

    @AfterClass
    public void deleteBoard() {
        RestAssured.given()
                .spec(reqSpecification)
                .pathParam("boardId", board.getBoardId())
                .when()
                .delete(BOARD_PATH + "/{boardId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$", Matchers.hasKey("_value"))
                .body("_value", Matchers.equalTo(null));
        card = null;
        board = null;
    }
}
