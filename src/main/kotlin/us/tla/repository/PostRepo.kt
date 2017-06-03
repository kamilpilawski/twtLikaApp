package us.tla.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import us.tla.model.Post
import java.util.*

/**
 * Created by Kamil on 24.05.2017.
 */
interface PostRepo : CrudRepository<Post, Long> {
    fun findAllByUserId(id: Long): Optional<List<Post>>

    fun findByTitle(title: String): Optional<List<Post>>

    fun findByTagsTitle(title: String): Optional<List<Post>>

    fun findByTagsId(id: Long): Optional<List<Post>>

    @Query("SELECT * FROM post where post.idpost in (SELECT likes.post_idpost FROM likes WHERE likes.user_iduser = ?1)", nativeQuery = true)
    fun findLikedPosts(userId: Long): Optional<List<Post>>
}