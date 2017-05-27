package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.Post
import us.tla.model.Tag
import us.tla.model.User
import us.tla.repository.PostRepo

/**
 * Created by Kamil on 24.05.2017.
 */
@RestController
@RequestMapping("api/post")
@CrossOrigin(origins = arrayOf("http://localhost:8081"))
class PostController {

    companion object : KLogging()

    @Autowired
    lateinit var postRepo: PostRepo

    @PostMapping("save")
    fun save(@RequestBody post: Post): ResponseEntity<Post> {
        logger.info { "addPost: $post" }
        val savedPost = postRepo.save(post)
        return ResponseEntity(savedPost, HttpStatus.OK)
    }

    @PutMapping("edit")
    fun edit(@RequestBody post: Post): ResponseEntity<Post> {
        logger.info { "editPost: $post" }
        val savedPost = postRepo.save(post)
        return ResponseEntity(savedPost, HttpStatus.OK)
    }

    @GetMapping("user/{userId}")
    fun findByUserId(@PathVariable userId: Long): ResponseEntity<List<Post>> {
        logger.info { "find post by user id: $userId" }
        val post = postRepo.findAllByUserId(userId)
        logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                post.orElse(emptyList()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @DeleteMapping("delete")
    fun delete(@RequestParam postId: Long): ResponseEntity<User> {
        logger.info { "destroy post: $postId" }
        postRepo.deleteById(postId)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("title/{title}")
    fun findByTitle(@PathVariable title: String): ResponseEntity<List<Post>> {
        logger.info { "find post by title: $title" }
        val post = postRepo.findByTitle(title)
        logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                post.orElse(emptyList()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("tag/title/{title}")
    fun findByTagTitle(@PathVariable title: String): ResponseEntity<List<Post>> {
        logger.info { "find post by user id: $title" }
        val post = postRepo.findByTagsTitle(title)
        logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                post.orElse(emptyList()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("tag/{id}")
    fun findByTagId(@PathVariable id: Long): ResponseEntity<List<Post>> {
        logger.info { "find post by tag id: $id" }
        val post = postRepo.findByTagsId(id)
        logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                post.orElse(emptyList()),
                if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

}