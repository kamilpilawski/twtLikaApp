package us.tla.model

import javax.persistence.*

/**
 * Created by Kamil on 03.06.2017.
 */
@Entity
data class Follow(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idfollow")
        val idfollow: Long = 0,

        @Column(name = "user_iduser")
        val userId: Long = 0,

        @Column(name = "followed_userid")
        val followedUserId: Long? = null,

        @Column(name = "tag_tagid")
        val tagId: Long? = null
)