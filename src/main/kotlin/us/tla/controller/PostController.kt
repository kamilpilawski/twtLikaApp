package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import us.tla.model.Post
import us.tla.model.User
import us.tla.repository.PostRepo

/**
 * Created by Kamil on 24.05.2017.
 */
@RestController
@RequestMapping("api/post")
class PostController {

    companion object : KLogging()

    @Autowired
    lateinit var postRepo: PostRepo

    @GetMapping("/user/{userId}")
    fun findByUserId(@PathVariable userId: Long): ResponseEntity<Post> {
        logger.info { "find post by user id: $userId" }
        val post =postRepo.findAllByUserId(userId)
        logger.info { "Result: ${post.orElse(Post())}" }

        return ResponseEntity(
                post.orElse(Post()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }
}