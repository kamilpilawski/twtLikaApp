package us.tla.config

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import us.tla.service.security.CurrentUserDetailService
import javax.servlet.http.HttpServletResponse


/**
 * Created by Kamil on 31.05.2017.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("us.tla.service.security")
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    companion object : KLogging()

    @Autowired
    lateinit var currUserDetailService: CurrentUserDetailService

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/*.js", "/*.css", "/*.ico", "/images/*", "/fonts/*")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/api/user/save", "/static/**").permitAll()
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

                    logger.info("User ${auth.name} has logged in.")

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
                .userDetailsService(currUserDetailService)
//                .passwordEncoder(BCryptPasswordEncoder())//todo zrobic haslsa BCRYPT
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.addAllowedMethod("*")
        configuration.addAllowedHeader("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)

        return source
    }

}
