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

public class SoundMethodTests
    extends BaseTest {

    private static final String pathRequest = Directory.SOUND.getValue();
    private static final String REPETITION_COUNT = "repetitionCount";
    private static final String SOUND_COUNT = "soundCount";

    @Test(description = "Проверка метода \"Крякать\" в случае, если количество повторений - 0, количество звуков - 2")
    @CitrusTest
    public void checkSoundMethodWhenRepetitionCountIs0SoundCountIs2(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String repetitionCountValue = "0";
        String soundCountValue = "2";
        String jsonPath = "$.sound";
        String expectedSound = "";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(REPETITION_COUNT, repetitionCountValue)
                        .queryParam(SOUND_COUNT, soundCountValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedSound)));
    }

    // TODO: QA-1 - Не описано в документации
    @Test(description = "Проверка метода \"Крякать\" в случае, если количество повторений - -1, количество звуков - 2")
    @CitrusTest
    public void checkSoundMethodWhenRepetitionCountIsNegative1SoundCountIs2(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String repetitionCountValue = "-1";
        String soundCountValue = "2";
        String jsonPath = "$.message";
        String expectedMessage = "Invalid parameter value";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(REPETITION_COUNT, repetitionCountValue)
                        .queryParam(SOUND_COUNT, soundCountValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedMessage)));
    }

    @Test(description = "Проверка метода \"Крякать\" в случае, если количество повторений - 1, количество звуков - 2")
    @CitrusTest
    public void checkSoundMethodWhenRepetitionCountIs1SoundCountIs2(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String repetitionCountValue = "1";
        String soundCountValue = "2";
        String jsonPath = "$.sound";
        String expectedSound = "quack-quack";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(REPETITION_COUNT, repetitionCountValue)
                        .queryParam(SOUND_COUNT, soundCountValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedSound)));
    }

    @Test(description = "Проверка метода \"Крякать\" в случае, если количество повторений - 3, количество звуков - 0")
    @CitrusTest
    public void checkSoundMethodWhenRepetitionCountIs3SoundCountIs0(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String repetitionCountValue = "3";
        String soundCountValue = "0";
        String jsonPath = "$.sound";
        String expectedSound = "";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(REPETITION_COUNT, repetitionCountValue)
                        .queryParam(SOUND_COUNT, soundCountValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedSound)));
    }


    // TODO: QA-1 - Не описано в документации
    @Test(description = "Проверка метода \"Крякать\" в случае, если количество повторений - 3, количество звуков - -1")
    @CitrusTest
    public void checkSoundMethodWhenRepetitionCountIs3SoundCountIsNegative1(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String repetitionCountValue = "3";
        String soundCountValue = "-1";
        String jsonPath = "$.message";
        String expectedMessage = "Invalid parameter value";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(REPETITION_COUNT, repetitionCountValue)
                        .queryParam(SOUND_COUNT, soundCountValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedMessage)));
    }

    @Test(description = "Проверка метода \"Крякать\" в случае, если количество повторений - 3, количество звуков - 1")
    @CitrusTest
    public void checkSoundMethodWhenRepetitionCountIs3SoundCountIs1(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String repetitionCountValue = "3";
        String soundCountValue = "1";
        String jsonPath = "$.sound";
        String expectedSound = "quack, quack, quack";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(REPETITION_COUNT, repetitionCountValue)
                        .queryParam(SOUND_COUNT, soundCountValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedSound)));
    }

    @Test(description = "Проверка метода \"Крякать\" в случае, если количество повторений - 3, количество звуков - 2")
    @CitrusTest
    public void checkSoundMethodWhenRepetitionCountIs3SoundCountIs2(
        @Optional @CitrusResource TestActionRunner actions) {

        // prepare
        String repetitionCountValue = "3";
        String soundCountValue = "2";
        String jsonPath = "$.sound";
        String expectedSound = "quack-quack, quack-quack, quack-quack";

        // do
        actions.$(http().client(duckClient)
                        .send()
                        .get(pathRequest)
                        .queryParam(REPETITION_COUNT, repetitionCountValue)
                        .queryParam(SOUND_COUNT, soundCountValue));

        // check
        actions.$(http().client(duckClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .type(MessageType.JSON)
                        .validate(jsonPath().expression(jsonPath, expectedSound)));
    }
}
