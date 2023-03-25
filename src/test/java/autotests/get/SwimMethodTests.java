package autotests.get;

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

public class SwimMethodTests
    extends BaseTest {

    private static final String pathRequest = Directory.SWIM.getValue();

    @Test(description = "Проверка метода \"Плыть\"")
    @CitrusTest
    public void checkSwimMethod(@Optional @CitrusResource TestActionRunner actions) {

        String jsonPath = "$.message";
        String expectedMessage = "I'm swimming";

        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest));

        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedMessage)));
    }
}
