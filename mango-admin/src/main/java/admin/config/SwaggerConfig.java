package admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*	public Docket createRestApi(Environment envi){
	//		设置要显示的Swagger环境
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					*//*.enable(true)   //enbale是否启动swagger，如果为false则swagger不能在浏览器访问*//*
				.select()
				.apis(RequestHandlerSelectors.basePackage("admin.**"))
				.paths(PathSelectors.any())
				.build();

	}

	private ApiInfo apiInfo() {
			return new ApiInfoBuilder()
					.title("Mango项目API接口文档")
					.version("1.0")
					.description("api描述")
					.build();
		}*/
	@Bean
	public Docket createRestApi() {
		// 添加请求参数，我们这里把token作为请求头部参数传入后端
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameterBuilder.name("token").description("令牌")
				.modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		parameters.add(parameterBuilder.build());
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
				.build().globalOperationParameters(parameters);
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//        		.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("使用Swagger2 构建RESTful APIS - zy") //接口管理文档首页显示
				.description("zy - Swagger使用演示")//API的描述
				.termsOfServiceUrl("www.footmark.top")//网站url等
				.version("1.0")
				.build();
	}

}


