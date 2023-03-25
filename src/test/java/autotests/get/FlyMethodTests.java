package autotests.get;

import autotests.BaseTest;
import autotests.Directory;
import com.consol.citrus.TestActionRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import static com.consol.citrus.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;

public class FlyMethodTests
    extends BaseTest {

    private static final String pathRequest = Directory.FLY.getValue();
    private static final String WINGS_STATE = "wingsState";

    @Test(description = "Проверка метода \"Лететь\" в случае, если состояние крыльев - ACTIVE")
    @CitrusTest
    public void checkFLyMethodWhenWingsAreActive(
        @Optional @CitrusResource TestActionRunner actions) {
        // prepare
        String wingsStateValue = "ACTIVE";
        String jsonPath = "$.message";
        String expectedMessage = "I'm flying";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(WINGS_STATE, wingsStateValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedMessage)));
    }

    @Test(description = "Проверка метода \"Лететь\" в случае, если состояние крыльев - FIXED")
    @CitrusTest
    public void checkFLyMethodWhenWingsAreFixed(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String wingsStateValue = "FIXED";
        String jsonPath = "$.message";
        String expectedMessage = "I can't fly";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(WINGS_STATE, wingsStateValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedMessage)));
    }


    // TODO: QA-1 - Не опсиано в документации
    @Test(description = "Проверка метода \"Лететь\" в случае, если состояние крыльев - --")
    @CitrusTest
    public void checkFLyMethodWhenWingsStateAreUnknown(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String jsonPath = "$.message";
        String expectedMessage = "No wings detectable";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.NOT_FOUND)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedMessage)));
    }
}
