package us.tla.repository

import org.springframework.data.repository.CrudRepository
import us.tla.model.Comment
import java.util.*

/**
 * Created by Kamil on 25.05.2017.
 */
interface CommentRepo : CrudRepository<Comment, Long> {
    fun findAllByUserId(userId: Long): Optional<List<Comment>>
    fun findAllByPostId(postId: Long): Optional<List<Comment>>
    fun findByCommentTagsTitle(title: String): Optional<List<Comment>>
    fun findByCommentTagsId(id: Long): Optional<List<Comment>>
}