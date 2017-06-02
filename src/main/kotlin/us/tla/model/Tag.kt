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

        @OneToMany(mappedBy = "tags", fetch = FetchType.LAZY)
        @JsonBackReference("tagPosts")
        val posts: List<Post>? = null,

        @OneToMany(mappedBy = "commentTags", fetch = FetchType.LAZY)
        @JsonBackReference("tagComments")
        val comments: List<Comment>? = null
) {
    override fun toString(): String {
        return "Tag(id=$id, title='$title')"
    }

}