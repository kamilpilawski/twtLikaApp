package us.tla.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource

/**
 * Created by Kamil on 31.05.2017.
 */
@Configuration
@EnableConfigurationProperties
class DataSourceConfig {


    @Bean(name = arrayOf("dataSource"))
    fun dataSource(): DriverManagerDataSource {
        val driverManagerDataSource = DriverManagerDataSource()
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver")
        driverManagerDataSource.url = "jdbc:mysql://79.137.85.13/twt"
        driverManagerDataSource.username = "twt"
        driverManagerDataSource.password = "twt"
        return driverManagerDataSource
    }
}