package us.tla.controller


import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.User
import us.tla.repository.UserRepo
import java.util.logging.Logger

/**
 * Created by Kamil on 12.03.2017.
 */
@RestController
@RequestMapping("api/user")
class UserController {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    lateinit var userRepo: UserRepo

    @PostMapping("save")
    fun save(@RequestBody user: User): ResponseEntity<User> {
        logger.info("addUser: $user")
        val savedUser = userRepo.save(user)
        return ResponseEntity(savedUser, HttpStatus.OK)
    }

    @PutMapping("edit")
    fun edit(@RequestBody user: User): ResponseEntity<User> {
        logger.info("editUser: $user")
        val savedUser = userRepo.save(user)
        return ResponseEntity(savedUser, HttpStatus.OK)
    }

    @DeleteMapping("delete")
    fun delete(@RequestParam userId: Long): ResponseEntity<User> {
        logger.info("destroyUser: $userId")
        userRepo.delete(userId)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping("{id}", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun find(@PathVariable id: Long): ResponseEntity<User?> {
        logger.info("findUser: $id")
        val users = userRepo.findAll()
        println(users.joinToString("\n"))
        val user = users.first()
        logger.info("Result: $user")
        return ResponseEntity(user, HttpStatus.OK)
    }

    @RequestMapping("list", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun list(): ResponseEntity<MutableIterable<User>> {
        logger.info("list users")
        val users = userRepo.findAll()
        return ResponseEntity(users, HttpStatus.OK)
    }


}