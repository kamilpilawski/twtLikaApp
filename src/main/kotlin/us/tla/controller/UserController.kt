package us.tla.controller


import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
        val savedUser = userRepo.save(user)
        return ResponseEntity(savedUser, HttpStatus.OK)
    }

    @PutMapping("edit")
    fun edit(@RequestBody user: User): ResponseEntity<User> {
        logger.info { "editUser: $user" }
        val savedUser = userRepo.save(user)
        return ResponseEntity(savedUser, HttpStatus.OK)
    }

    @DeleteMapping("delete")
    fun delete(@RequestParam userId: Long): ResponseEntity<User> {
        logger.info { "destroyUser: $userId" }
        userRepo.deleteById(userId)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("{id}")
    fun find(@PathVariable id: Long): ResponseEntity<User> {
        logger.info { "findUser: $id" }
        val user = userRepo.findById(id)
        logger.info { "Result: ${user.orElse(User())}" }

        return ResponseEntity(
                user.orElse(User()),
                if (user.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )


    }

    @GetMapping("list")
    fun list(): ResponseEntity<MutableIterable<User>> {
        logger.info { "list users" }
        val users = userRepo.findAll()
        return ResponseEntity(users, HttpStatus.OK)
    }


}