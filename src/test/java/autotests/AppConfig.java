package autotests;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {
    @Value("${yellow.rubber.duck.url:}")
    private String duckUrl;

    @Value("/api/duck")
    private String basePath;

}
