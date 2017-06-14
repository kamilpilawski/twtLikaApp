package us.tla.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.DriverManagerDataSource

/**
 * Created by Kamil on 31.05.2017.
 */
@Configuration
@EnableConfigurationProperties
class DataSourceConfig {

    @Autowired
    lateinit var env: Environment

    @Bean(name = arrayOf("dataSource"))
    fun dataSource(): DriverManagerDataSource {
        val driverManagerDataSource = DriverManagerDataSource()
        driverManagerDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"))
        driverManagerDataSource.url = env.getProperty("spring.datasource.url")
        driverManagerDataSource.username = env.getProperty("spring.datasource.username")
        driverManagerDataSource.password = env.getProperty("spring.datasource.password")
        return driverManagerDataSource
    }
}