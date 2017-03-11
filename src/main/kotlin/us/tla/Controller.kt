package us.tla

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Kamil on 10.03.2017.
 */
@RestController()
@RequestMapping("/api")
class Controller {


    @RequestMapping("hi", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun hi(): ResponseEntity<Person> {
        println("hi endpoint fired")
        return ResponseEntity(Person("mate"), HttpStatus.OK)
    }


}


data class Person(val name: String)