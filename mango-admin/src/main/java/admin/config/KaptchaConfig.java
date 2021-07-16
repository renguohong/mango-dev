package admin.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/* * @ClassName : KaptchaConfig
 * @Description :验证码配置类
 * @Author: gino
 * @Date: 2021/7/003
 * @Time: 11:01

 */
@Configuration
public class KaptchaConfig {
	@Bean
	public DefaultKaptcha producer(){
		Properties properties = new Properties();
		properties.put("captcha.broader","no");
		properties.put("captcha.Reproducer.font.color","black");
		properties.put("captcha.Reproducer.char.space","5");
		Config config = new Config(properties);
		DefaultKaptcha defaultKaptcha= new DefaultKaptcha();
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
}
