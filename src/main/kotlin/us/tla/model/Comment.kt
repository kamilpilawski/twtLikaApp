package us.tla.model

import com.fasterxml.jackson.annotation.JsonFormat
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Europe/Warsaw")
        val createDate: Date? = null,

        @Column(name = "edit_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Europe/Warsaw")
        val editDate: Date? = null,

        @OneToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "tag_relation",
                joinColumns = arrayOf(JoinColumn(name = "comment_idcomment", referencedColumnName = "idcomment")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "tag_idtag", referencedColumnName = "idtag")))
        val commentTags: MutableList<Tag>? = null

) {
}