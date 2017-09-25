package com.chw.spb;

import com.chw.spb.config.ApplicationStartup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @author chw
 *
 */
 

@SpringBootApplication
@MapperScan("com.chw.spb.**.dao")
@EnableCaching
public class SpbApplication {

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(SpbApplication.class);
		sa.addListeners(new ApplicationStartup());
		sa.run(args);
	}
	
	


	/*public ApiInfo apiInfo(){
		return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .termsOfServiceUrl("http://blog.didispace.com/")
                
                .version("1.0")
                .build();

	}
	
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }*/


}
