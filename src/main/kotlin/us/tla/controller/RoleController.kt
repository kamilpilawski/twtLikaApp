package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import us.tla.model.Role
import us.tla.repository.RoleRepo

/**
 * Created by Kamil on 25.05.2017.
 */
@RestController
@RequestMapping("api/role")
class RoleController {

    companion object : KLogging()

    @Autowired
    lateinit var roleRepo: RoleRepo

    @GetMapping("list")
    fun listRoles(): ResponseEntity<Iterable<Role>> {
        logger.info("list roles")
        val roles = roleRepo.findAll().asIterable()

        return if (roles.count() > 0) {
            ResponseEntity(roles, HttpStatus.OK)
        } else {
            ResponseEntity(emptyList(), HttpStatus.NOT_FOUND)
        }
    }
}