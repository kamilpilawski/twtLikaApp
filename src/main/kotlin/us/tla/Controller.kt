package us.tla

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Kamil on 10.03.2017.
 */

@RestController
class Controller {

    @RequestMapping("/hi", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    @ResponseBody
    fun hi(): String {
        println("hi endpoint fired")
        return "data: greetings friend"
    }
}