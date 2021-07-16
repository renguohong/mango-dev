package admin.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * cors技术
 * 此技术解决跨域问题*/
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //允许跨域访问的路径
				.allowedOriginPatterns("*") //允许跨域访问的资源
				.allowedMethods("*") //允许请求的方法
		        .maxAge(168000) //预检间隔时间
		        .allowedHeaders("*")//允许头部设置
		        .allowCredentials(true);  //是否发送cookie
	}



	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//调用父类的配置
		WebMvcConfigurer.super.configureMessageConverters(converters);
		//创建FastJson的消息转换器
		FastJsonHttpMessageConverter convert = new FastJsonHttpMessageConverter();
		//创建FastJson的配置对象
		FastJsonConfig config = new FastJsonConfig();
		//对Json数据进行格式化
		config.setSerializerFeatures(SerializerFeature.PrettyFormat,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteMapNullValue,
				//禁止循环引用
				SerializerFeature.DisableCircularReferenceDetect);
		config.setDateFormat("yyyy-MM-dd HH:mm:ss");
		config.setCharset(Charset.forName("UTF-8"));
		convert.setFastJsonConfig(config);
		convert.setSupportedMediaTypes(getSupportedMediaTypes());
		converters.add(convert);
	}

	public List<MediaType> getSupportedMediaTypes() {
		//创建fastJson消息转换器
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XML);
		supportedMediaTypes.add(MediaType.IMAGE_GIF);
		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
		supportedMediaTypes.add(MediaType.IMAGE_PNG);
		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.TEXT_XML);
		supportedMediaTypes.add(MediaType.ALL);
		return supportedMediaTypes;
	}

}


