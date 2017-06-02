package us.tla.model

import javax.persistence.*

/**
 * Created by Kamil on 03.06.2017.
 */
@Entity
data class Likes(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idlike")
        val id: Long = 0,

        @Column(name = "comment_idcomment")
        val commentId: Long? = null,

        @Column(name = "post_idpost")
        val postId: Long? = null,

        @Column(name = "user_iduser")
        val userId: Long = 0
)