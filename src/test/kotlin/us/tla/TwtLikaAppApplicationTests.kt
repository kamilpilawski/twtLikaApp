package us.tla

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import us.tla.model.User

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TwtLikaAppApplicationTests {

    @Test
    fun contextLoads() {
        println("Context loaded")
    }

    @Test
    fun userCtr() {
        val x = TestRestTemplate()
        val id = 1
        val body = x.getForObject("http://localhost:8080/api/user/$id", String.javaClass)

        val ent = x.getForEntity("http://localhost:8080/api/user/$id", String.javaClass)

        println("takie body $body")

        assert(null != body)
    }

}
