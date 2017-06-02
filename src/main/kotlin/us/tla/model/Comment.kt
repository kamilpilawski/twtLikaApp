package us.tla.model

import java.util.*
import javax.persistence.*

/**
 * Created by Kamil on 24.05.2017.
 */
@Entity
data class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idcomment")
        val id: Long = 0,

        val content: String = "",

        @Column(name = "user_iduser")
        val userId: Long = 0,

        @Column(name = "post_idpost")
        val postId: Long = 0,

        @Column(name = "create_date")
        val createDate: Date?,

        @Column(name = "edit_date")
        val editDate: Date?,

        @OneToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "tag_relation",
                joinColumns = arrayOf(JoinColumn(name = "comment_idcomment", referencedColumnName = "idcomment")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "tag_idtag", referencedColumnName = "idtag")))
        val commentTags: MutableList<Tag>? = null

) {
}