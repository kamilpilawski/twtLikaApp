package us.tla.service.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import us.tla.repository.UserRepo

/**
 * Created by Kamil on 15.06.2017.
 */
@Service
class CurrentUserDetailService : UserDetailsService {
    @Autowired
    lateinit var userRepo: UserRepo

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepo.findByEmail(username).orElseThrow({
            UsernameNotFoundException("User with email $username was not found.")
        })
        return CurrentUser(user)
    }
}