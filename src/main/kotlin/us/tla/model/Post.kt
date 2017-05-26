package us.tla.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * Created by Kamil on 24.05.2017.
 */
@Entity
data class Post(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idpost")
        val id: Long = 0,
        val title: String = "",
        val content: String = "",
        @Column(name = "user_iduser")
        val userId: Long = 0,

        @OneToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "tag_relation",
                joinColumns = arrayOf(JoinColumn(name = "post_idpost", referencedColumnName = "idpost")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "tag_idtag", referencedColumnName = "idtag")))
        @JsonBackReference("tagPosts")
        val tags: MutableList<Tag>? = null
) {
    override fun toString(): String {
        return "Post(id=$id, title='$title', content='$content', userId=$userId)"
    }


}