package autotests.post;

import autotests.BaseTest;
import autotests.Directory;
import com.consol.citrus.TestActionRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import static com.consol.citrus.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;

public class PropertiesMethodTests
    extends BaseTest {

    private static final String pathRequest = Directory.PROPERTIES.getValue();

    // TODO: QA-1 - Неверные значение параметра metersHeight
    @Test(description = "Проверка цвета уточки в случае, если материал - резина")
    @CitrusTest
    public void checkColorWhenMaterialIsRubber(@Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String body = "{ \"material\": \"rubber\" }";
        String colorJsonPath = "$.color";
        String materialJsonPath = "$.material";
        String heightJsonPath = "$.metersHeight";

        String expectedColor = "yellow";
        String expectedMaterial = "rubber";
        Double expectedHeight = 3.0;

        //do
        actions.$(http().client(duckClient)
                        .send()
                        .post(pathRequest)
                        .message()
                        .header("Content-Type", "application/json")
                        .body(body));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(colorJsonPath, expectedColor))
                        .validate(jsonPath().expression(materialJsonPath, expectedMaterial))
                        .validate(jsonPath().expression(heightJsonPath, expectedHeight)));
    }

    @Test(description = "Проверка цвета уточки в случае, если материал - не резина")
    @CitrusTest
    public void checkColorWhenMaterialIsNotRubber(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String body = "{ \"material\": \"gold\" }";
        String jsonPath = "$";
        String emptyJson = "{}";

        //do
        actions.$(http().client(duckClient)
                        .send()
                        .post(pathRequest)
                        .message()
                        .header("Content-Type", "application/json")
                        .body(body));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, emptyJson)));
    }

    @Test(description = "Проверка цвета уточки в случае, если материал неизвестен")
    @CitrusTest
    public void checkColorWhenMaterialIsUnknown(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String body = "{ \"material\": \"\" }";
        String jsonPath = "$";
        String emptyJson = "{}";

        //do
        actions.$(http().client(duckClient)
                        .send()
                        .post(pathRequest)
                        .message()
                        .header("Content-Type", "application/json")
                        .body(body));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, emptyJson)));
    }

    // TODO: QA-1 - Не описано в документации
    @Test(description = "Проверка цвета уточки в случае, если json body пустой")
    @CitrusTest
    public void checkColorWhenJsonBodyIsEmpty(@Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String body = "{}";

        String colorJsonPath = "$.color";
        String materialJsonPath = "$.material";
        String heightJsonPath = "$.metersHeight";

        String expectedColor = "yellow";
        String expectedMaterial = "rubber";
        Double expectedHeight = 3.0;

        //do
        actions.$(http().client(duckClient)
                        .send()
                        .post(pathRequest)
                        .message()
                        .header("Content-Type", "application/json")
                        .body(body));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(colorJsonPath, expectedColor))
                        .validate(jsonPath().expression(materialJsonPath, expectedMaterial))
                        .validate(jsonPath().expression(heightJsonPath, expectedHeight)));
    }


    // TODO: QA-1 - Не описано в документации
    @Test(description = "Проверка цвета уточки в случае, если некорректные параметры в POST")
    @CitrusTest
    public void checkColorWhenPOSTBodyParametersAreIncorrect(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String body = "{ \"size\": 10 }";

        String colorJsonPath = "$.color";
        String materialJsonPath = "$.material";
        String heightJsonPath = "$.metersHeight";

        String expectedColor = "yellow";
        String expectedMaterial = "rubber";
        Double expectedHeight = 3.0;

        //do
        actions.$(http().client(duckClient)
                        .send()
                        .post(pathRequest)
                        .message()
                        .header("Content-Type", "application/json")
                        .body(body));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(colorJsonPath, expectedColor))
                        .validate(jsonPath().expression(materialJsonPath, expectedMaterial))
                        .validate(jsonPath().expression(heightJsonPath, expectedHeight)));
    }
}
