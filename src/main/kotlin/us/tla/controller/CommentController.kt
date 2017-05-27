package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.Comment
import us.tla.model.User
import us.tla.repository.CommentRepo

/**
 * Created by Kamil on 25.05.2017.
 */
@RestController
@RequestMapping("api/comment")
@CrossOrigin(origins = arrayOf("http://localhost:8081"))
class CommentController {

    companion object : KLogging()

    @Autowired
    lateinit var commentRepo: CommentRepo

    @PostMapping("save")
    fun save(@RequestBody comment: Comment): ResponseEntity<Comment> {
        logger.info { "add Coment: $comment" }
        return ResponseEntity(commentRepo.save(comment), HttpStatus.OK)
    }


    @DeleteMapping("delete")
    fun delete(@RequestParam commentId: Long): ResponseEntity<User> {
        logger.info { "destroy comment: $commentId" }
        commentRepo.deleteById(commentId)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("user/{userId}")
    fun findByUserId(@PathVariable userId: Long): ResponseEntity<List<Comment>> {
        logger.info { "find comment by user id: $userId" }
        val comments = commentRepo.findAllByUserId(userId)
        PostController.logger.info { "Result: ${comments.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                comments.orElse(emptyList()),
                if (comments.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("post/{postId}")
    fun findByPostId(@PathVariable postId: Long): ResponseEntity<List<Comment>> {
        logger.info { "find comment by post id: $postId" }
        val comments = commentRepo.findAllByPostId(postId)
        PostController.logger.info { "Result: ${comments.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                comments.orElse(emptyList()),
                if (comments.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("tag/title/{title}")
    fun findByTagTitle(@PathVariable title: String): ResponseEntity<List<Comment>> {
        logger.info { "find post by user id: $title" }
        val comments = commentRepo.findByCommentTagsTitle(title)
        logger.info { "Result: ${comments.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                comments.orElse(emptyList()),
                if (comments.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("tag/{id}")
    fun findByTagId(@PathVariable id: Long): ResponseEntity<List<Comment>> {
        logger.info { "find post by user id: $id" }
        val comments = commentRepo.findByCommentTagsId(id)
        logger.info { "Result: ${comments.orElse(emptyList()).joinToString("\n")}" }

        return ResponseEntity(
                comments.orElse(emptyList()),
                if (comments.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }
}