package br.com.jdsb.cfc;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"br.com.jdsb.negocio"})
@EnableJpaRepositories("br.com.jdsb.negocio")
@PropertySource(value = "classpath:application.properties")
public class SpringJpaConfig {

	@Value(value = "${jdbc.user}")
	private String username;
	@Value(value = "${jdbc.pass}")
	private String password;
	@Value(value = "${jdbc.driver}")
	private String driver;
	@Value(value = "${jdbc.url}")
	private String url;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("br.com.jdsb.negocio");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setJpaProperties(jpaProperties());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(factory);
		tx.setJpaDialect(new HibernateJpaDialect());
		return tx;
	}

	private Properties jpaProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.show_sql", "false");
//		props.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.format_sql", "false");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		return props;
	}
}
