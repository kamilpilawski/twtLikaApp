package us.tla.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Kamil on 24.05.2017.
 */
@Entity
data class Post(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idpost")
        val id: Long = 0,

        @Column(name = "user_iduser")
        val userId: Long = 0,

        @NotNull
        @Size(min = 2, max = 50)
        val title: String = "",

        @Size(min = 2, max = 350)
        @NotNull
        val content: String = "",

        @Column(name = "create_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Europe/Warsaw")
        val createDate: Date? = null,

        @Column(name = "edit_date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Europe/Warsaw")
        val editDate: Date? = null,

        @OneToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "tag_relation",
                joinColumns = arrayOf(JoinColumn(name = "post_idpost", referencedColumnName = "idpost")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "tag_idtag", referencedColumnName = "idtag")))
        val tags: MutableList<Tag>? = null
) {
    override fun toString(): String {
        return "Post(id=$id, title='$title', content='$content', userId=$userId)"
    }


}