package us.tla.service

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import us.tla.model.Post
import us.tla.repository.PostRepo
import us.tla.service.security.CurrentUser

/**
 * Created by Kamil on 15.06.2017.
 */
@Service
class PostService {


    companion object : KLogging()

    @Autowired
    lateinit var postRepo: PostRepo

    fun getLikedPosts(auth: Authentication): List<Post> {

        val currentUser = auth.principal as CurrentUser
        logger.info { "Getting liked posts by user ${currentUser.name}" }

        val posts = postRepo.findLikedPosts(currentUser.user.id)
        return when {
            posts.isPresent -> {
                posts.get().map { it.copy(liked = true) }
            }
            else -> {
                emptyList()
            }
        }


    }
}