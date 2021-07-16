package admin.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperties {
//	jdbc
	private String driverClassName;
	private String url;
	private String username;
	private String password;
//	jdbc connection pool
	private int initialSize;
	private  int maxActive =100;
	private long maxWit;
	private long timeBetweenEvictionRunsMillis;
	private long minEvictableIdleTimeMillis;
	private String validationQuery;

	private boolean testWhileIdle;
	private boolean testOnBorrow;
	private  boolean testOnReturn;
	private boolean poolPreparedStatements;
	private int maxPoolPreparedStatementPerConnectionSize;
//	filter
	private String filters;

}
