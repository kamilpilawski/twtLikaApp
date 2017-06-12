package us.tla.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.User
import us.tla.repository.UserRepo

/**
 * Created by Kamil on 10.03.2017.
 */
@RestController()
@RequestMapping("api")
class Controller {

    private val logger = LoggerFactory.getLogger(Controller::class.java)

    @Autowired
    lateinit var userRepo: UserRepo

    @RequestMapping("hi", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun hi(): ResponseEntity<MutableIterable<User>> {
        println("hi endpoint fired")
        val users = userRepo.findAll()
        return ResponseEntity(users, HttpStatus.OK)
    }


}


