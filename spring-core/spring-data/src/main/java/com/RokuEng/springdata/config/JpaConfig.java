package com.RokuEng.springdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.RokuEng.springdata")
public class JpaConfig {

	@Value("${spring.data.url}")
	private String DB_URL;
	@Value("${spring.data.username}")
	private String DB_USERNAME;
	@Value("${spring.data.password}")
	private String DB_PASSWORD;
	@Value("${spring.data.driver}")
	private String DB_DRIVER;

	@Value("${spring.data.packagesToScan}")
	private String PACKAGES_TO_SCAN;

	@Value("${spring.data.persistenceUnitName}")
	private String PERSISTENCE_UNIT_NAME;

	@Value("${hibernate.showSQL}")
	private Boolean SHOW_SQL;

	@Value("${hibernate.generateDDL}")
	private Boolean GENERATE_DDL;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();

		datasource.setDriverClassName(DB_DRIVER);
		datasource.setUsername(DB_USERNAME);
		datasource.setPassword(DB_PASSWORD);
		datasource.setUrl(DB_URL);

		return datasource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaVendorAdapter(vendorAdaptor());
		entityManagerFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		entityManagerFactory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager(
		EntityManagerFactory entityManagerFactory
	) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	private HibernateJpaVendorAdapter vendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(SHOW_SQL);
		vendorAdapter.setGenerateDdl(GENERATE_DDL);
		return vendorAdapter;
	}
}
