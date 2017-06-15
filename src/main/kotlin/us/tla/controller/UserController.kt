package us.tla.controller


import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import us.tla.model.User
import us.tla.repository.UserRepo

/**
 * Created by Kamil on 12.03.2017.
 */
@RestController
@RequestMapping("api/user")
class UserController {

    companion object : KLogging()

    @Autowired
    lateinit var userRepo: UserRepo

    @PostMapping("save")
    fun save(@RequestBody user: User): ResponseEntity<User> {
        logger.info { "addUser: $user" }
        return ResponseEntity(userRepo.save(user), HttpStatus.OK)
    }

    @PreAuthorize("hasAnyAuthority('admin, mod')")
    @PutMapping("edit")
    fun edit(@RequestBody user: User): ResponseEntity<User> {
        logger.info { "editUser: $user" }
        val savedUser = userRepo.save(user)
        return ResponseEntity(savedUser, HttpStatus.OK)
    }

    @PreAuthorize("hasAnyAuthority('admin, mod')")
    @DeleteMapping("delete")
    fun delete(@RequestParam userId: Long): ResponseEntity<User> {
        logger.info { "destroyUser: $userId" }
        userRepo.deleteById(userId)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("id/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<User> {
        logger.info { "findUser: $id" }
        val user = userRepo.findById(id)
        logger.info { "Result: ${user.orElse(User())}" }

        return ResponseEntity(
                user.orElse(User()),
                if (user.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("{email}")
    fun findByEmail(@PathVariable email: String): ResponseEntity<User> {
        logger.info { "findUser: $email" }
        val user = userRepo.findByEmail(email)
        logger.info { "Result: ${user.orElse(User())}" }

        return ResponseEntity(
                user.orElse(User()),
                if (user.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @PreAuthorize("hasAnyAuthority('admin, mod')")
    @GetMapping("list")
    fun list(): ResponseEntity<MutableIterable<User>> {
        logger.info { "list users" }
        val users = userRepo.findAll()
        return ResponseEntity(users, HttpStatus.OK)
    }

    @GetMapping("list/role/{roleId}")
    fun listByRoleId(@PathVariable roleId: Long): ResponseEntity<List<User>> {
        logger.info { "list users by role id:$roleId" }
        val users = userRepo.findAllByRolesId(roleId)

        return ResponseEntity(
                users.orElse(emptyList()),
                if (users.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }


}