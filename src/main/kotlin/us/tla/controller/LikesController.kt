package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.Likes
import us.tla.repository.LikesRepo
import javax.validation.Valid

/**
 * Created by Kamil on 03.06.2017.
 */
@RestController
@RequestMapping("api/likes")
@CrossOrigin(origins = arrayOf("*"))
class LikesController {
    companion object : KLogging()

    @Autowired
    lateinit var likesRepo: LikesRepo

    @PostMapping("add")
    fun addLike(@RequestBody @Valid likes: Likes): ResponseEntity<Likes> {
        logger.info { "add likes: $likes" }
        try {
            return ResponseEntity(likesRepo.save(likes), HttpStatus.OK)
        } catch(ex: Exception) {
            logger.info { "Exc message: ${ex.message}" }
        }
        return ResponseEntity(Likes(), HttpStatus.CONFLICT)
    }

    @DeleteMapping("remove/post")
    fun removePostLike(@RequestParam userId: Long, postId: Long): HttpStatus {
        logger.info { "remove like userId:$userId, postId:$postId" }
        val like = likesRepo.findByUserIdAndPostId(userId, postId)
        if (null != like) {
            likesRepo.delete(like)
            return HttpStatus.OK
        } else {
            return HttpStatus.CONFLICT
        }
    }

    @DeleteMapping("remove/comment")
    fun removeCommentLike(@RequestParam userId: Long, commentId: Long): HttpStatus {
        logger.info { "remove like userId:$userId, commentId:$commentId" }
        val like = likesRepo.findByUserIdAndCommentId(userId, commentId)
        if (null != like) {
            likesRepo.delete(like)
            return HttpStatus.OK
        } else {
            return HttpStatus.CONFLICT
        }
    }

    @GetMapping("list/{userId}")
    fun getUserLike(@PathVariable userId: Long): ResponseEntity<List<Likes>> {
        logger.info { "find user $userId likes." }
        val likes = likesRepo.findByUserId(userId)
        return ResponseEntity(
                likes.orElse(listOf(Likes())),
                if (likes.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

}