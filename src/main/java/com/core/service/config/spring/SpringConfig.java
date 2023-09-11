package com.core.service.config.spring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {
        ContextInstanceDataAutoConfiguration.class,
        ContextStackAutoConfiguration.class,
        ContextRegionProviderAutoConfiguration.class
})
public class SpringConfig {
    /*
        해당 설정은 Spring Cloud Aws 의존성이 추가 후
        특정 엔드포인트가 AWS EC2 인스턴스에서만 접근이 되기 떄문에 로컬환경에서 에러 발생
        해당 에러를 없애기 위해 로컬 환경에서 실행할 경우 설정을 통해 해당 클래스들을 자동 설정에서 제외한 것
        이후 EC2 main 브랜치에서 실행할 경우 해당 클래스를 제거해야 함
    */
}
