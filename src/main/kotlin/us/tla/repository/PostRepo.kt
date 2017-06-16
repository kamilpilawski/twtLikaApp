package us.tla.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import us.tla.model.Post
import java.util.*

/**
 * Created by Kamil on 24.05.2017.
 */
interface PostRepo : CrudRepository<Post, Long> {
    fun findAllByUserIdOrderByCreateDateDesc(id: Long): Optional<List<Post>>

    fun findByTitle(title: String): Optional<List<Post>>

    fun findByTagsTitle(title: String): Optional<List<Post>>

    fun findByTagsId(id: Long): Optional<List<Post>>

    @Query("SELECT * FROM post where post.idpost in (SELECT likes.post_idpost FROM likes WHERE likes.user_iduser = ?1) order by post.create_date desc", nativeQuery = true)
    fun findLikedPosts(userId: Long): Optional<List<Post>>

    @Query("SELECT * FROM post where post.user_iduser in (SELECT follow.followed_userid FROM follow WHERE follow.user_iduser = ?1) order by post.create_date desc", nativeQuery = true)
    fun findFollowedUsersPosts(userId: Long): Optional<List<Post>>

}