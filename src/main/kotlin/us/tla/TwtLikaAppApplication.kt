package us.tla

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TwtLikaAppApplication

fun main(args: Array<String>) {
    SpringApplication.run(TwtLikaAppApplication::class.java, *args)
}
