package us.tla.repository

import org.springframework.data.repository.CrudRepository
import us.tla.model.Follow

/**
 * Created by Kamil on 03.06.2017.
 */
interface FollowRepo : CrudRepository<Follow, Long> {
    fun findByUserIdAndFollowedUserId(userId: Long, followedUserId: Long): Follow
    fun findByUserIdAndTagId(userId: Long, tagId: Long): Follow
}