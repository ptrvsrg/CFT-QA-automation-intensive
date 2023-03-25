package autotests;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Import(BaseTest.class)
@Configuration
@RequiredArgsConstructor
public class EndpointConfig {

    private final AppConfig appConfig;

    @Bean("duckClient")
    public HttpClient duckClient() {
        return CitrusEndpoints
                .http()
                .client()
                .requestUrl(appConfig.getDuckUrl() + appConfig.getBasePath())
                .build();
    }


}
