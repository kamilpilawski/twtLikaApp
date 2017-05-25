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
    fun findByUserId(@PathVariable userId: Long): ResponseEntity<List<Post>> {
        logger.info { "find post by user id: $userId" }
        val post = postRepo.findAllByUserId(userId)
        logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                post.orElse(emptyList()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("/title/{title}")
    fun findByTitle(@PathVariable title: String): ResponseEntity<List<Post>> {
        logger.info { "find post by title: $title" }
        val post = postRepo.findByTitle(title)
        logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                post.orElse(emptyList()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("/tag/title/{title}")
    fun findByTagTitle(@PathVariable title: String): ResponseEntity<List<Post>> {
        logger.info { "find post by user id: $title" }
        val post = postRepo.findByTagsTitle(title)
        logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                post.orElse(emptyList()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("/tag/{id}")
    fun findByTagId(@PathVariable id: Long): ResponseEntity<List<Post>> {
        logger.info { "find post by user id: $id" }
        val post = postRepo.findByTagsId(id)
        logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                post.orElse(emptyList()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

}