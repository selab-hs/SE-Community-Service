package com.core.service.config.swagger;

import com.core.service.auth.infrastructure.annotation.AuthMember;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.core.service.common.header.HeaderUtil.SELAB_AUTH_HEADER_KEY;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig implements WebMvcConfigurer {
    @PostConstruct
    public void init() {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(AuthMember.class);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components().addSecuritySchemes(SELAB_AUTH_HEADER_KEY,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name(SELAB_AUTH_HEADER_KEY)
                                .description("인증이 필요한 경우 ex) X-SELAB-AUTH-TOKEN xxxxxxx")
                ))
                .security(List.of(new SecurityRequirement().addList(SELAB_AUTH_HEADER_KEY)))
                .externalDocs(new ExternalDocumentation())
                .info(new Info()
                        .title("SE.LAB API Documents")
                        .description("SE.LAB Public APIs For Clients.")
                        .contact(new Contact().name("Contact SE.LAB"))
                        .version("v1.0.0")
                        .license(new License().name("SE.LAB. All rights reserved.").url("https://github.com/selab-hs"))
                );
    }
}
