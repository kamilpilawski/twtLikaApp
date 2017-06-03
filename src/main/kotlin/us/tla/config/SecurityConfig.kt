package us.tla.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import javax.servlet.http.HttpServletResponse
import javax.sql.DataSource
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*


/**
 * Created by Kamil on 31.05.2017.
 */
@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    @Qualifier("dataSource")
    lateinit var ds: DataSource

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/api/user/save").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint { req, res, exc ->
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, exc.message)
                }
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .successHandler { req, res, auth ->
                    res.status = HttpServletResponse.SC_OK
                    res.contentType = "application/json"
                    res.writer.apply {
                        write(ObjectMapper().writeValueAsString(ResponseEntity(auth.principal, HttpStatus.OK)))
                        flush()
                        close()
                    }
                }
                .failureHandler { req, res, exc ->
                    res.status = HttpServletResponse.SC_FORBIDDEN
                }
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler { req, res, auth ->
                    res.status = HttpServletResponse.SC_OK
                }
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery("select email as username, password, enabled from user where email = ?")
                .authoritiesByUsernameQuery("select user.email as username, role.title as role from user join user_role on user.iduser = user_role.user_iduser join role on user_role.role_idrole = role.idrole where user.email= ?")
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = Arrays.asList("https://example.com")
//        configuration.allowedMethods = Arrays.asList("GET", "POST")
        configuration.allowCredentials = true
        configuration.allowedOrigins = listOf("*")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        return source
    }

}
