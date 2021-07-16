package com.louis.mango.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mango.backup.datasource")
@Data
public class BackUpDataSourceProperties {
	private String host;
	private String userName;
	private String password;
	private String database;
}
