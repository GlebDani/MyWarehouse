package ru.danilenko.mywarehouse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("MyWarehouse")
                .version("1.0")
                .description("Endpoints for WareHouse test task");

        return new OpenAPI().info(info);
    }
}
