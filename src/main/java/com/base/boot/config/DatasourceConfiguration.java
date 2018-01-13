package com.base.boot.config;

import com.base.orm.core.CustomRepositoryFactoryBean;
import com.base.orm.core.DBUtil;
import com.base.orm.core.HibernateUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages ={"com.dy.*.repository"},
        entityManagerFactoryRef = "autoEntityManagerFactory",
        transactionManagerRef = "autoTransactionManager",
        repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class
)
public class DatasourceConfiguration  {

    @Bean
    @Qualifier
    @Primary
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
    }

    @Bean(name = "jpaVendorAdapter")
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setDatabasePlatform(HibernateUtil.getDialect(dataSource()));
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);//自动生成表
        return hibernateJpaVendorAdapter;
    }


    @Bean(name = "autoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean  entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean  = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.dy.*.domain");
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        Map<String,Object> map = new HashMap<>();
        map.put("hibernate.query.substitutions","true 1, false 0");
        map.put("hibernate.default_batch_fetch_size",16);
        map.put("hibernate.max_fetch_depth",2);
        map.put("hibernate.bytecode.use_reflection_optimizer",true);
        map.put("hibernate.cache.use_second_level_cache",false);
        map.put("hibernate.cache.use_query_cache",false);
        map.put("hibernate.generate_statistics",false);
        entityManagerFactoryBean.setJpaPropertyMap(map);
        entityManagerFactoryBean.setMappingResources("META-INF/orm.xml");
        return entityManagerFactoryBean;
    }


    @Bean(name = "autoTransactionManager")
    public PlatformTransactionManager getTransactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return  tm;
    }

    @Bean
    public DBUtil dbUtil(){

        return new DBUtil();
    }

}
