package us.tla.controller

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.TagRelation
import us.tla.repository.TagRelationRepo
import javax.validation.Valid

/**
 * Created by Kamil on 02.06.2017.
 */
@RestController
@RequestMapping("api/hash")
@CrossOrigin(origins = arrayOf("*"))
class TagRelController {
    companion object : KLogging()

    @Autowired
    lateinit var tagRelationRepo: TagRelationRepo

    @DeleteMapping("remove/post")
    fun removeHash(@RequestParam postId: Long, @RequestParam tagId: Long): HttpStatus {
        logger.info { "remove hashtag by post Id: $postId, tagId: $tagId" }
        val hash = tagRelationRepo.findByPostIdpostAndTagIdtag(postId, tagId)
        if (null != hash) {
            tagRelationRepo.delete(hash)
            return HttpStatus.OK
        } else {
            return HttpStatus.CONFLICT
        }
    }

    @DeleteMapping("remove/comment")
    fun removeHashComment(@RequestParam commentId: Long, @RequestParam tagId: Long): HttpStatus {
        logger.info { "remove hashtag by post Id: $commentId, tagId: $tagId" }
        val hash = tagRelationRepo.findByCommentIdcommentAndTagIdtag(commentId, tagId)
        if (null != hash) {
            tagRelationRepo.delete(hash)
            return HttpStatus.OK
        } else {
            return HttpStatus.CONFLICT
        }
    }

    @PostMapping("add")
    fun addHash(@RequestBody @Valid tagRel: TagRelation): ResponseEntity<TagRelation> {
        logger.info { "add hash $tagRel" }
        try {
            return ResponseEntity(tagRelationRepo.save(tagRel), HttpStatus.OK)
        } catch (ex: Exception) {
            logger.info { "Exc message: ${ex.message}" }
        }
        return ResponseEntity(TagRelation(), HttpStatus.CONFLICT)
    }
}