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
import us.tla.service.LikesService
import us.tla.service.PostService
import us.tla.service.security.CurrentUser

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

    @Autowired
    lateinit var postService: PostService

    @Autowired
    lateinit var likesService: LikesService

    @GetMapping("liked")
    fun likedPosts(auth: Authentication): ResponseEntity<List<Post>> {

        val likedPosts = postService.getLikedPosts(auth)

        return ResponseEntity(likedPosts, if (likedPosts.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK)
    }

    @PostMapping("save")
    fun save(@RequestBody request: Map<String, Object>, auth: Authentication): ResponseEntity<Post> {
        val currentUser = auth.principal as CurrentUser

        logger.info { "addPost: $request by user: ${currentUser.name}" }

        val savedPost = postService.createPostWithTags(Post(title = request["title"].toString(), content = request["content"].toString(), userId = currentUser.user.id), request["tags"])

        return ResponseEntity(savedPost.orElse(Post()), if (savedPost.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND)
    }

    @PutMapping("edit")
    fun edit(@RequestBody post: Post): ResponseEntity<Post> {
        logger.info { "editPost: $post" }
        val savedPost = postRepo.save(post)
        return ResponseEntity(savedPost, HttpStatus.OK)
    }

    @GetMapping("user/{userId}")
    fun findByUserId(@PathVariable userId: Long, auth: Authentication): ResponseEntity<List<Post>> {
        logger.info { "find post by user id: $userId" }
        val plainPosts = postRepo.findAllByUserIdOrderByCreateDateDesc(userId)
        val currentUser = auth.principal as CurrentUser
        val isLikedPosts = plainPosts.get().map { it.copy(liked = likesService.isLiked(currentUser.user.id, it.id)) }
        logger.info { "Result: ${isLikedPosts.joinToString("\n")}" }

        return ResponseEntity(
                isLikedPosts,
                if (isLikedPosts.isNotEmpty()) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @PostMapping("user/email")
    fun findByUserEmail(@RequestParam email: String): ResponseEntity<List<Post>> {
        logger.info { "find post by user emamil: $email" }
        val user = userRepo.findByEmail(email)

        when {
            user.isPresent -> {
                val post = postRepo.findAllByUserIdOrderByCreateDateDesc(user.get().id)
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

    @GetMapping("own")
    fun findOwnPosts(auth: Authentication): ResponseEntity<List<Post>> {
        logger.info { "find own posts, user: ${auth.name}" }
        val user = userRepo.findByEmail(auth.name)

        when {
            user.isPresent -> {
                val post = postRepo.findAllByUserIdOrderByCreateDateDesc(user.get().id)
                logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

                return ResponseEntity(
                        post.orElse(emptyList()),
                        if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
                )
            }
            else -> return ResponseEntity(emptyList(), HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("followed/users")
    fun findFollowedUsersPosts(auth: Authentication): ResponseEntity<List<Post>> {
        logger.info { "find own posts, user: ${auth.name}" }
        val user = userRepo.findByEmail(auth.name)

        when {
            user.isPresent -> {
                val post = postRepo.findFollowedUsersPosts(user.get().id)
                logger.info { "Result: ${post.orElse(emptyList()).joinToString("\n")}" }

                return ResponseEntity(
                        post.orElse(emptyList()),
                        if (post.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
                )
            }
            else -> return ResponseEntity(emptyList(), HttpStatus.NOT_FOUND)
        }
    }


}