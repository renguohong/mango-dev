package admin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 通过注解配置mybatis的mapper映射*/
@Configuration
@MapperScan("admin.dao")   //扫描啊的是接口
public class MybatisConfig {
	@Autowired
	private DataSource dataSource;

	/**
	 * 配置sqlsessionfactoryBean对象
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesPackage("admin.pojo");   //扫描的是实体类
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml")); //扫描映射文件
		return sessionFactory.getObject();
	}
}

