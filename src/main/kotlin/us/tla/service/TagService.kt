package us.tla.service

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.tla.model.Tag
import us.tla.model.TagRelation
import us.tla.repository.TagRelationRepo
import us.tla.repository.TagRepo
import java.util.*

/**
 * Created by Kamil on 15.06.2017.
 */
@Service
class TagService {

    companion object : KLogging()

    @Autowired
    lateinit var tagRepo: TagRepo

    @Autowired
    lateinit var tagRelRepo: TagRelationRepo

    fun addTag(tagTitle: String, postId: Long): TagRelation {

        var tag = tagRepo.findByTitle(tagTitle)

        return if (tag.isPresent) {
            saveTagRel(tag.get(), postId)
        } else {
            saveTagRel(tagRepo.save(Tag(title = tagTitle)), postId)
        }

    }

    private fun saveTagRel(tag: Tag, postId: Long): TagRelation {
        val tagRel = TagRelation(tagIdtag = tag.id, postIdpost = postId)
        return tagRelRepo.save(tagRel)
    }


}