package com.sjh.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource(value = "classpath:druid.properties", ignoreResourceNotFound = true)
@Slf4j
public class DruidConfig {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.type}")
	private String type;

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.datasource.filters}")
	private String filters;

	@Value("${spring.datasource.maxActive}")
	private Integer maxActive;

	@Value("${spring.datasource.initialSize}")
	private Integer initialSize;

	@Value("${spring.datasource.maxWait}")
	private Integer maxWait;

	@Value("${spring.datasource.minIdle}")
	private Integer minIdle;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private Integer timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;

	@Value("${spring.datasource.queryTimeout}")
	private Integer queryTimeout;

	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private Integer maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.logSlowSql}")
	private String logSlowSql;

	@Value("${spring.datasource.connectionProperties}")
	private String connectionProperties;

	@Bean
	public ServletRegistrationBean druidServlet() {
		log.debug("===== 注册 ServletRegistrationBean =====");
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		//白名单，若不设置则允许所有
		reg.addInitParameter("allow", "127.0.0.1,192.168.227.245");
		//黑名单，优先级高于白名单
		reg.addInitParameter("deny", "192.168.227.60");
		reg.addInitParameter("loginUsername", "sjh");
		reg.addInitParameter("loginPassword", "123456");
		reg.addInitParameter("logSlowSql", logSlowSql);
		return reg;
	}

	@Bean
	public FilterRegistrationBean druidFilter() {
		log.debug("===== 注册 FilterRegistrationBean =====");
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(new WebStatFilter());
		filter.addUrlPatterns("/*");
		filter.addInitParameter("exclusions", "*.js,*.gif,*.jgp,*.png,*.css,*.ico,/druid/*");
		filter.addInitParameter("profileEnable", "true");
		return filter;
	}

	@Bean
	@Primary
	public DataSource druidDataSource() {
		log.debug("===== 加载 DruidDataSource =====");
		DruidDataSource druidDataSource = new DruidDataSource();
		try {
			druidDataSource.setUrl(url);
			druidDataSource.setUsername(username);
			druidDataSource.setPassword(password);
			// 如果不配置，druid 会根据 url 自动识别dbType,并选择相应的 driverClassName
			druidDataSource.setDriverClassName(driverClassName);

			//druidDataSource.setFilters(filters);
			druidDataSource.setMaxActive(maxActive);
			druidDataSource.setInitialSize(initialSize);
			druidDataSource.setMaxWait(maxWait);
			druidDataSource.setMinIdle(minIdle);
			druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
			druidDataSource.setQueryTimeout(queryTimeout);
			druidDataSource.setValidationQuery(validationQuery);
			druidDataSource.setTestWhileIdle(testWhileIdle);
			druidDataSource.setTestOnBorrow(testOnBorrow);
			druidDataSource.setTestOnReturn(testOnReturn);
			druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
			druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
			druidDataSource.setConnectionProperties(connectionProperties);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}

}
