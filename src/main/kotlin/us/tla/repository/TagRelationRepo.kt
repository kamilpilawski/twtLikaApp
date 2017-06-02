package us.tla.repository

import org.springframework.data.repository.CrudRepository
import us.tla.model.TagRelation

/**
 * Created by Kamil on 02.06.2017.
 */
interface TagRelationRepo : CrudRepository<TagRelation, Long> {

    fun findByPostIdpostAndTagIdtag(postId: Long, tagId: Long): TagRelation
}