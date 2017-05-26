package us.tla.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * Created by Kamil on 12.03.2017.
 */
@Entity
data class Tag(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idtag")
        val id: Long = 0,
        val title: String = "",

        @Transient
        @OneToMany(mappedBy = "tags", fetch = FetchType.EAGER)
        @JsonBackReference("tagPosts")
        val posts: List<Post>? = null,

        @Transient
        @OneToMany(mappedBy = "commentTags", fetch = FetchType.EAGER)
        @JsonBackReference("tagComments")
        val comments: List<Comment>? = null
) {
    override fun toString(): String {
        return "Tag(id=$id, title='$title')"
    }

}