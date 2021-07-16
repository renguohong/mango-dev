package admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({DruidDataSourceProperties.class})
public class DruidConfig {
    @Autowired
	private DruidDataSourceProperties properties;

	/**
	* 需要将properties进行依赖注入，否则会报错
	* 错误信息如下：
	* datasource-1 init error
	* 下面会报一个空指针异常
	* */
    @Bean
	@ConditionalOnMissingBean
    public DataSource druidDataSource(DruidDataSourceProperties properties){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(properties.getDriverClassName());
		druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMaxWait(properties.getMaxWit());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
		return  druidDataSource;
	}

	/**
	*@ConditionalOnMissingBean 用来修饰bean 当bean被注册后在注册相同的bean不会成功，他会保证bean只有一个
	 * 其他注解
	 * @ConditionalOnBean // 当给定的在bean存在时,则实例化当前Bean
	 * @ConditionalOnMissingBean // 当给定的在bean不存在时,则实例化当前Bean
	 * @ConditionalOnClass // 当给定的类名在类路径上存在，则实例化当前Bean
	 * @ConditionalOnMissingClass // 当给定的类名在类路径上不存在，则实例化当前Bean
	 * 实例代码
	 *通过其三个属性prefix,name以及havingValue来实现的，其中prefix表示配置文件里节点前缀，name用来从application.properties中读取某个属性值，havingValue表示目标值。
	 * 如果该值为空，则返回false;
	 * 如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。
	 * 返回值为false，则该configuration不生效；为true则生效
	 * @Configuration
	 * @ConditionalOnProperty(prefix="lind.redis",name = "enable", havingValue = "true")
	 * public class RedisConfig {
	 *   @Bean
	 *   public RedisMap redisMap(){
	 *     return new RedisMapImpl();
	 *   }
	 * }*/
	@Bean
	@ConditionalOnMissingBean
//	注册servlet信息，配置监控试图
	public ServletRegistrationBean<Servlet> druidServlet(){
    	ServletRegistrationBean<Servlet> servletServletRegistrationBean= new
				ServletRegistrationBean<Servlet>(new StatViewServlet(),"/druid/*");
//    	白名单
		servletServletRegistrationBean.addInitParameter("allow","127.0.0.1,192.168.117.39");
//		ip黑名单（存在共同时，deny优于allow）
//		如果满足deny的提示的话：sorry，you are not permitted to view this page
		servletServletRegistrationBean.addInitParameter("deny","192.168.1.119");
//		登录用于查看信息的账号密码，用于登录druid后台
		servletServletRegistrationBean.addInitParameter("loginUsername","admin");
		servletServletRegistrationBean.addInitParameter("loginPassword","admin");
//		是否能够重置数据
		servletServletRegistrationBean.addInitParameter("resetEnable","true");
		return  servletServletRegistrationBean;
	}


/*注册filter信息，监控拦截器*/
	/**
	 * 注册Filter信息, 监控拦截器
	 *
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}



}
