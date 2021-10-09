package ru.onemore.vtbhack.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({
	"ru.onemore.vtbhack.back.entity",
	"ru.onemore.vtbhack.back.jooq.tables.pojos"
})
@EnableJpaRepositories("ru.onemore.vtbhack.back.repository")
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener(ServerProperties serverProperties) {
		return (evt) -> {
			Integer port = serverProperties.getPort();
			System.out.println("");
			System.out.println("=============================");
			System.out.println("Server started: http://localhost:" + port);
			System.out.println("=============================");
			System.out.println("");
		};
	}

}
