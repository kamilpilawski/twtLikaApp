package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import us.tla.model.Post
import us.tla.model.User
import us.tla.repository.PostRepo
import us.tla.repository.UserRepo
import us.tla.service.security.CurrentUser
import javax.validation.Valid

/**
 * Created by Kamil on 24.05.2017.
 */
@RestController
@RequestMapping("api/post")
class PostController {

    companion object : KLogging()

    @Autowired
    lateinit var postRepo: PostRepo

    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping("liked")
    fun likedPosts(auth: Authentication): ResponseEntity<List<Post>> {

        val currentUser = auth.principal as CurrentUser
        logger.info { "Getting liked posts by user ${currentUser.name}" }

        val likedPosts = postRepo.findLikedPosts(currentUser.user.id)

        return ResponseEntity(
                likedPosts.orElse(listOf(Post())),
                if (likedPosts.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @PostMapping("save")
    fun save(@RequestBody @Valid post: Post, auth: Authentication): ResponseEntity<Post> {
        val currentUser = auth.principal as CurrentUser

        logger.info { "addPost: $post by user: ${currentUser.name}" }

        val savedPost = postRepo.save(post.copy(userId = currentUser.user.id))
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

    @PostMapping("user/email")
    fun findByUserEmail(@RequestParam email: String): ResponseEntity<List<Post>> {
        logger.info { "find post by user emamil: $email" }
        val user = userRepo.findByEmail(email)

        when {
            user.isPresent -> {
                val post = postRepo.findAllByUserId(user.get().id)
                logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

                return ResponseEntity(
                        post.orElse(emptyList()),
                        if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
                )
            }
            else -> return ResponseEntity(emptyList(), HttpStatus.NOT_FOUND)
        }
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