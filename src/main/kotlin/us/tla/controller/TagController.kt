package us.tla.controller


import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import us.tla.model.Tag
import us.tla.repository.TagRepo

/**
 * Created by Kamil on 26.05.2017.
 */
@RestController
@RequestMapping("api/tag")
class TagController {

    companion object : KLogging()

    @Autowired
    lateinit var tagRepo: TagRepo

    @GetMapping("list")
    fun list(pageable: Pageable): ResponseEntity<Page<Tag>> {
        UserController.logger.info { "list tags" }
        val tags = tagRepo.findAll(pageable)
        return ResponseEntity(tags, HttpStatus.OK)
    }

    @GetMapping("id/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Tag> {
        logger.info { "find tag by id: $id" }
        val tag = tagRepo.findById(id)
        logger.info { "Result: $tag" }

        return ResponseEntity(tag.orElse(Tag()),
                if (tag.isPresent) HttpStatus.OK else HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("delete")
    fun delete(@RequestParam tagId: Long): ResponseEntity<Tag> {
        logger.info { "destroy Tag: $tagId" }
        tagRepo.deleteById(tagId)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("save")
    fun save(@RequestBody tag: Tag): ResponseEntity<Tag> {
        logger.info { "add tag: $tag" }
        return ResponseEntity(tagRepo.save(tag), HttpStatus.OK)
    }
}