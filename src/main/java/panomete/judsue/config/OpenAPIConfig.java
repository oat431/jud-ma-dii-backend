package panomete.judsue.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Jud Sue Jud Jang API",
                version = "1.0.0",
                description = "the api documentation for gosoft assignment to identify our skill"
        )
)
public class OpenAPIConfig { }