package us.tla.service

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import us.tla.model.Post
import us.tla.repository.FollowRepo
import us.tla.repository.PostRepo
import us.tla.service.security.CurrentUser

/**
 * Created by Kamil on 15.06.2017.
 */
@Service
class FollowService {

    companion object : KLogging()

    @Autowired
    lateinit var followRepo: FollowRepo

    fun getLikedPosts(auth: Authentication) {

//        val currUser = auth.principal as CurrentUser
//        val follows = followRepo.findByUserId(currUser.user.id)
//
//        PostService.logger.info { "Getting liked posts by user ${currentUser.name}" }
//
//        val posts = postRepo.findLikedPosts(currentUser.user.id)
//        return when {
//            posts.isPresent -> {
//                posts.get().map { it.copy(liked = true) }
//            }
//            else -> {
//                emptyList()
//            }
//        }
    }

    fun isFollowed(myId: Long, hisId: Long): Boolean {
        return followRepo.findByUserIdAndFollowedUserId(myId, hisId).isPresent
    }

}