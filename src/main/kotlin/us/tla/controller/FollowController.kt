package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.Follow
import us.tla.model.Likes
import us.tla.repository.FollowRepo
import us.tla.repository.UserRepo
import java.security.Principal
import javax.validation.Valid

/**
 * Created by Kamil on 03.06.2017.
 */
@RestController
@RequestMapping("api/follow")
@CrossOrigin(origins = arrayOf("*"))
class FollowController {
    companion object : KLogging()

    @Autowired
    lateinit var followRepo: FollowRepo

    @Autowired
    lateinit var userRepo: UserRepo


    @PostMapping("/")
    fun follow(@RequestBody @Valid follow: Follow, principal: Principal): ResponseEntity<Follow> {
        logger.info { "Trying to follow: $follow" }
        val user = userRepo.findByEmail(principal.name).get()
        val flw = follow.copy(userId = user.id)
        try {
            return ResponseEntity(followRepo.save(flw), HttpStatus.OK)
        } catch(ex: Exception) {
            LikesController.logger.info { "Exc message: ${ex.message}" }
        }
        return ResponseEntity(Follow(), HttpStatus.CONFLICT)
    }

    @DeleteMapping("stop/user")
    fun stopFollowingUser(@RequestParam followedUserId: Long, principal: Principal): HttpStatus {
        logger.info { "removing follow on user: $followedUserId" }
        val user = userRepo.findByEmail(principal.name).get()
        val follow = followRepo.findByUserIdAndFollowedUserId(user.id, followedUserId)
        if (null != follow) {
            followRepo.delete(follow)
            return HttpStatus.OK
        } else {
            return HttpStatus.CONFLICT
        }
    }

    @DeleteMapping("stop/tag")
    fun stopFollowingTag(@RequestParam tagId: Long, principal: Principal): HttpStatus {
        logger.info { "removing follow on tag: $tagId" }
        val follow = followRepo.findByUserIdAndTagId(userRepo.findByEmail(principal.name).get().id, tagId)
        if (null != follow) {
            followRepo.delete(follow)
            return HttpStatus.OK
        } else {
            return HttpStatus.CONFLICT
        }
    }
}