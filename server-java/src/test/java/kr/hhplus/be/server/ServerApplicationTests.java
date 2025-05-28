package kr.hhplus.be.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.ecommerce.domain")
@EntityScan(basePackages = "com.example.ecommerce.domain")
@ComponentScan(basePackages = {
		"com.example.ecommerce",     // domain, application, interface, infra
		"kr.hhplus.be.server"        // 현재 서버 시작점
})
class ServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
