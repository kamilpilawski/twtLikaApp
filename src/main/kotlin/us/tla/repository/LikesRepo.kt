package us.tla.repository

import org.springframework.data.repository.CrudRepository
import us.tla.model.Likes
import java.util.*

/**
 * Created by Kamil on 03.06.2017.
 */
interface LikesRepo : CrudRepository<Likes, Long> {
    fun findByUserIdAndPostId(userId: Long, postId: Long): Likes
    fun findByUserIdAndCommentId(userId: Long, commentId: Long): Likes
    fun findByUserId(userId: Long): Optional<List<Likes>>
}