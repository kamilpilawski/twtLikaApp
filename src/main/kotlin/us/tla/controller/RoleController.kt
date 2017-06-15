package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import us.tla.model.Role
import us.tla.repository.RoleRepo
import us.tla.repository.UserRepo
import java.security.Principal

/**
 * Created by Kamil on 25.05.2017.
 */
@RestController
@RequestMapping("api/role")
class RoleController {

    companion object : KLogging()

    @Autowired
    lateinit var roleRepo: RoleRepo

    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping("list")
    fun listRoles(principal: Principal): ResponseEntity<List<Role>> {
        logger.info("list roles")

        val user = userRepo.findByEmail(principal.name).get()

        val roles = roleRepo.findUserRoles(user.id)
        return ResponseEntity(
                roles.orElse(listOf(Role())),
                if (roles.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("auth") //todo front w ten sposob powinien sprawdzac czy jest zalogowany
    fun listRolesOther(auth: Authentication): ResponseEntity<MutableCollection<out GrantedAuthority>> {
        logger.info("list auth")

        return ResponseEntity(auth.authorities, HttpStatus.OK)
    }
}