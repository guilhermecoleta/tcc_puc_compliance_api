package puc.tcc.compliance.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = "puc.tcc.compliance.api")
@PropertySource(value = "classpath:application.yml")
@PropertySource(value = "classpath:application-${spring.profiles.active}.yml")
public class ComplianceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplianceApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

}
