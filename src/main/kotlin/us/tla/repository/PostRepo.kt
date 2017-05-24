package us.tla.repository

import org.springframework.data.repository.CrudRepository
import us.tla.model.Post
import java.util.*

/**
 * Created by Kamil on 24.05.2017.
 */
interface PostRepo : CrudRepository<Post, Long> {
    fun findAllByUserId(id: Long): Optional<Post>
}