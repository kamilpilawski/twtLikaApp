package us.tla.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.tla.repository.LikesRepo

/**
 * Created by Kamil on 16.06.2017.
 */
@Service
class LikesService {

    @Autowired
    lateinit var likesRepo: LikesRepo

    fun isLiked(userId: Long, postId: Long): Boolean {
        return likesRepo.findByUserIdAndPostId(userId, postId).isPresent
    }

}