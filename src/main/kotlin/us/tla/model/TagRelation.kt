package us.tla.model

import javax.persistence.*

/**
 * Created by Kamil on 02.06.2017.
 */
@Entity
data class TagRelation(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "tag_relationcol")
        val id: Long = 0,

        @Column(name = "tag_idtag")
        val tagIdtag: Long? = null,

        @Column(name = "post_idpost")
        val postIdpost: Long? = null,

        @Column(name = "comment_idcomment")
        val commentIdcomment: Long? = null
)
