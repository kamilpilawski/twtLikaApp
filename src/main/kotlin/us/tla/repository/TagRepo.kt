package us.tla.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import us.tla.model.Tag
import java.util.*

/**
 * Created by Kamil on 26.05.2017.
 */
interface TagRepo : JpaRepository<Tag, Long> {
    //    @Query("select t from Tag t join TagRelation tr on t.id = tr.tagIdtag where tr.postIdpost=?1")
    @Query("select tag.idtag, tag.title from tag join tag_relation on tag.idtag = tag_relation.tag_idtag where tag_relation.post_idpost=?1", nativeQuery = true)
    fun findAllByPostsId(postId: Long): List<Tag>
}