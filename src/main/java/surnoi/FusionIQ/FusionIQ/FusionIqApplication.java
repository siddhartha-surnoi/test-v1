package surnoi.FusionIQ.FusionIQ;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class  FusionIqApplication {

	public static void main(String[] args) {
		SpringApplication.run(FusionIqApplication.class, args);
	}
	@Bean
	public AmazonS3 amazonS3() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIATCKAT4K7JFPNYR57", "w7As/F5qo0OfD7hQtdeEYfCSCoBlHtpepH5LatME");
		return AmazonS3ClientBuilder.standard()
				.withRegion(Regions.AP_SOUTH_1)
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.build();
	}

	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurer() {

			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200","http://fusioniq0f0version1.s3-website.ap-south-1.amazonaws.com")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");


			}

		};
	}
}

