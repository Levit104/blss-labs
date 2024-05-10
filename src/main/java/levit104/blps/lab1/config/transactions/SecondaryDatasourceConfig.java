package levit104.blps.lab1.config.transactions;

import com.atomikos.spring.AtomikosDataSourceBean;
import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "levit104.blps.lab1.repos.secondary",
        entityManagerFactoryRef = "secondaryEntityManager"
)
public class SecondaryDatasourceConfig {
    @Value("${spring.datasource.secondary-url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean(initMethod = "init", destroyMethod = "close")
    public AtomikosDataSourceBean secondaryDataSource() {
        PGXADataSource xaDataSource = new PGXADataSource();
        xaDataSource.setUrl(dbUrl);
        xaDataSource.setUser(dbUsername);
        xaDataSource.setPassword(dbPassword);
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setXaDataSource(xaDataSource);
        dataSourceBean.setUniqueResourceName("secondary_db");
        dataSourceBean.setMaxPoolSize(10);
        return dataSourceBean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManager() throws IOException {
        Properties jpaProperties = new Properties();
        jpaProperties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJtaDataSource(secondaryDataSource());
        factory.setPackagesToScan("levit104.blps.lab1.models.secondary");
        factory.setPersistenceUnitName("postgres");
        factory.setJpaProperties(jpaProperties);

        return factory;
    }
}
