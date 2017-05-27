package us.tla.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import us.tla.model.User
import us.tla.repository.UserRepo
import java.util.logging.Logger

/**
 * Created by Kamil on 10.03.2017.
 */
@RestController()
@RequestMapping("api")
@CrossOrigin(origins = arrayOf("http://localhost:8081"))
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


