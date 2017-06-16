package us.tla.service

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import us.tla.model.Post
import us.tla.model.Tag
import us.tla.model.TagRelation
import us.tla.repository.PostRepo
import us.tla.repository.TagRelationRepo
import us.tla.repository.TagRepo
import us.tla.service.security.CurrentUser
import java.util.*

/**
 * Created by Kamil on 15.06.2017.
 */
@Service
class PostService {


    companion object : KLogging()

    @Autowired
    lateinit var postRepo: PostRepo

    @Autowired
    lateinit var tagRepo: TagRepo

    @Autowired
    lateinit var tagRelRepo: TagRelationRepo

    fun getLikedPosts(auth: Authentication): List<Post> {

        val currentUser = auth.principal as CurrentUser
        logger.info { "Getting liked posts by user ${currentUser.name}" }

        val posts = postRepo.findLikedPosts(currentUser.user.id)
        return when {
            posts.isPresent -> {
                posts.get().map { it.copy(liked = true) }
            }
            else -> {
                emptyList()
            }
        }


    }

    fun createPostWithTags(post: Post, tags: Object?): Optional<Post> {

        val savedPost = postRepo.save(post)
        logger.info("Saved Post: $savedPost")

        when {
            null != tags -> {
                val tagsList = tags as List<String>
                logger.info("Tagi z frontu (${tagsList.size}): $tagsList")

                val tagsFromRepo = tagRepo.findAllByTitleIn(tagsList)
                logger.info("Tagi z repo (${tagsFromRepo.get().size}): ${tagsFromRepo.get().size}")
                when {
                    tagsFromRepo.isPresent && tagsFromRepo.get().size == tagsList.size -> {
                        logger.info("tagow tyle samo, zapisuje te z repo do relacji")
                        tagsFromRepo.get().map {
                            tagRelRepo.save(TagRelation(tagIdtag = it.id, postIdpost = savedPost.id))
                        }
                    }
                    else -> {
                        logger.info("Tagow rozna ilosc, tworze nowe")
                        val newTags = tagsList.filter { !tagsFromRepo.get().map { it.title }.contains(it) }
                        logger.info(newTags.joinToString(","))

                        val savedNewTags = newTags.map { tagRepo.save(Tag(title = it)) }
                        savedNewTags.map { tagRelRepo.save(TagRelation(tagIdtag = it.id, postIdpost = savedPost.id)) }
                    }
                }
            }
        }
        return postRepo.findById(savedPost.id)
    }


}