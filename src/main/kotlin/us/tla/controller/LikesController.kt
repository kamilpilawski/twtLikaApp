package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.Likes
import us.tla.repository.LikesRepo
import us.tla.repository.UserRepo
import java.security.Principal
import javax.validation.Valid

/**
 * Created by Kamil on 03.06.2017.
 */
@RestController
@RequestMapping("api/likes")
class LikesController {
    companion object : KLogging()

    @Autowired
    lateinit var likesRepo: LikesRepo

    @Autowired
    lateinit var userRepo: UserRepo

    @PostMapping("add")
    fun addLike(@RequestBody @Valid likes: Likes, principal: Principal): ResponseEntity<Likes> {
        logger.info { "add likes: $likes" }
        val user = userRepo.findByEmail(principal.name).get()
        val lks = likes.copy(userId = user.id)
        try {
            return ResponseEntity(likesRepo.save(lks), HttpStatus.OK)
        } catch(ex: Exception) {
            logger.info { "Exc message: ${ex.message}" }
        }
        return ResponseEntity(Likes(), HttpStatus.CONFLICT)
    }

    @DeleteMapping("remove/post")
    fun removePostLike(@RequestParam postId: Long, principal: Principal): HttpStatus {
        logger.info { "remove likepostId: $postId" }
        val user = userRepo.findByEmail(principal.name).get()
        val like = likesRepo.findByUserIdAndPostId(user.id, postId)
        if (null != like) {
            likesRepo.delete(like)
            return HttpStatus.OK
        } else {
            return HttpStatus.CONFLICT
        }
    }

    @DeleteMapping("remove/comment")
    fun removeCommentLike(@RequestParam commentId: Long, principal: Principal): HttpStatus {
        logger.info { "remove like commentId:$commentId" }
        val user = userRepo.findByEmail(principal.name).get()
        val like = likesRepo.findByUserIdAndCommentId(user.id, commentId)
        if (null != like) {
            likesRepo.delete(like)
            return HttpStatus.OK
        } else {
            return HttpStatus.CONFLICT
        }
    }

    @GetMapping("list")
    fun getUserLike(principal: Principal): ResponseEntity<List<Likes>> {
        logger.info { "find user likes." }
        val user = userRepo.findByEmail(principal.name).get()
        val likes = likesRepo.findByUserId(user.id)
        return ResponseEntity(
                likes.orElse(listOf(Likes())),
                if (likes.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

}